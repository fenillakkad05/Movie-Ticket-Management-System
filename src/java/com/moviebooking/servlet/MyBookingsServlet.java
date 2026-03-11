package com.moviebooking.servlet;

import com.moviebooking.dao.BookingDAO;
import com.moviebooking.model.Booking;
import com.moviebooking.model.User;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mybookings")
public class MyBookingsServlet extends HttpServlet {
    
    private BookingDAO bookingDAO;
    
    @Override
    public void init() throws ServletException {
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
        
        User user = (User) session.getAttribute("user");
        List<Booking> bookings = bookingDAO.getUserBookings(user.getId());
        
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("mybookings.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}