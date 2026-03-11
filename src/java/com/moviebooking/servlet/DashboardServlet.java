package com.moviebooking.servlet;

import com.moviebooking.dao.MovieShowDAO;
import com.moviebooking.model.MovieShow;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    
    private MovieShowDAO movieShowDAO;
    
    @Override
    public void init() throws ServletException {
        movieShowDAO = new MovieShowDAO();
        System.out.println("DashboardServlet initialized");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("DashboardServlet: doGet called");
        
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("User not logged in, redirecting to login");
            response.sendRedirect("index.html");
            return;
        }
        
        // Get all movie shows
        System.out.println("Fetching movie shows...");
        List<MovieShow> movieShows = movieShowDAO.getAllMovieShows();
        
        System.out.println("Movie shows fetched: " + (movieShows != null ? movieShows.size() : "null"));
        
        if (movieShows != null) {
            for (MovieShow show : movieShows) {
                System.out.println("Show: " + show.getMovieName() + " at " + show.getCinemaName());
            }
        }
        
        request.setAttribute("movieShows", movieShows);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}