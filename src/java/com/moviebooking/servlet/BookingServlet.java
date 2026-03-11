package com.moviebooking.servlet;

import com.moviebooking.dao.BookingDAO;
import com.moviebooking.dao.MovieShowDAO;
import com.moviebooking.model.Booking;
import com.moviebooking.model.MovieShow;
import com.moviebooking.model.User;
import com.moviebooking.pattern.PaymentContext;
import com.moviebooking.pattern.PaymentStrategy;
import com.moviebooking.pattern.UPIPayment;
import com.moviebooking.pattern.CreditCardPayment;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {
    
    private MovieShowDAO movieShowDAO;
    private BookingDAO bookingDAO;
    
    @Override
    public void init() throws ServletException {
        movieShowDAO = new MovieShowDAO();
        bookingDAO = new BookingDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        
        String showIdStr = request.getParameter("showId");
        
        if (showIdStr != null && !showIdStr.isEmpty()) {
            try {
                int showId = Integer.parseInt(showIdStr);
                MovieShow show = movieShowDAO.getMovieShowById(showId);
                
                if (show != null) {
                    request.setAttribute("movieShow", show);
                    request.getRequestDispatcher("booking.jsp").forward(request, response);
                } else {
                    response.sendRedirect("dashboard");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("dashboard");
            }
        } else {
            response.sendRedirect("dashboard");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.html");
            return;
        }
        
        try {
            // Get booking details
            int showId = Integer.parseInt(request.getParameter("showId"));
            int seats = Integer.parseInt(request.getParameter("seats"));
            String paymentMethod = request.getParameter("paymentMethod");
            
            User user = (User) session.getAttribute("user");
            MovieShow show = movieShowDAO.getMovieShowById(showId);
            
            if (show == null || seats > show.getAvailableSeats()) {
                request.setAttribute("error", "Invalid booking or not enough seats available");
                request.setAttribute("movieShow", show);
                request.getRequestDispatcher("booking.jsp").forward(request, response);
                return;
            }
            
            double totalAmount = show.getPrice() * seats;
            
            // Strategy Pattern for Payment Processing
            PaymentContext paymentContext = new PaymentContext();
            PaymentStrategy strategy;
            
            if ("UPI".equals(paymentMethod)) {
                String upiId = request.getParameter("upiId");
                strategy = new UPIPayment(upiId);
            } else {
                String cardNumber = request.getParameter("cardNumber");
                String cardHolder = request.getParameter("cardHolder");
                String expiry = request.getParameter("expiry");
                String cvv = request.getParameter("cvv");
                strategy = new CreditCardPayment(cardNumber, cardHolder, expiry, cvv);
            }
            
            paymentContext.setPaymentStrategy(strategy);
            
            // Process payment
            boolean paymentSuccess = paymentContext.executePayment(totalAmount);
            
            if (paymentSuccess) {
                // Create booking
                Booking booking = new Booking();
                booking.setUserId(user.getId());
                booking.setShowId(showId);
                booking.setSeats(seats);
                booking.setTotalAmount(totalAmount);
                booking.setPaymentMethod(paymentContext.getPaymentMethodName());
                
                if (bookingDAO.createBooking(booking)) {
                    // Update available seats
                    movieShowDAO.updateAvailableSeats(showId, seats);
                    
                    // Set success attributes
                    request.setAttribute("success", true);
                    request.setAttribute("movieName", show.getMovieName());
                    request.setAttribute("cinemaName", show.getCinemaName());
                    request.setAttribute("showDate", show.getShowDate());
                    request.setAttribute("showTime", show.getShowTime());
                    request.setAttribute("seats", seats);
                    request.setAttribute("totalAmount", totalAmount);
                    request.setAttribute("paymentMethod", paymentContext.getPaymentMethodName());
                    
                    request.getRequestDispatcher("success.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Booking failed. Please try again.");
                    request.setAttribute("movieShow", show);
                    request.getRequestDispatcher("booking.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Payment failed. Please try again.");
                request.setAttribute("movieShow", show);
                request.getRequestDispatcher("booking.jsp").forward(request, response);
            }
            
        } catch (NumberFormatException e) {
            response.sendRedirect("dashboard");
        }
    }
}