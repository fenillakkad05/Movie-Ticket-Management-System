<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.moviebooking.model.MovieShow" %>
<%@ page import="com.moviebooking.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - MovieBook</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        List<MovieShow> movieShows = (List<MovieShow>) request.getAttribute("movieShows");
    %>
    
    <div class="dashboard-container">
        <!-- Header -->
        <header class="header">
            <div class="header-left">
                <h1>🎬 Movie Book</h1>
            </div>
            <div class="header-right">
                <span class="welcome-text">Welcome, <%= user.getName() %></span>
                <a href="mybookings" class="nav-link">My Bookings</a>
                <a href="logout" class="nav-link logout-btn">Logout</a>
            </div>
        </header>
        
        <!-- Main Content -->
        <main class="main-content">
            <div class="page-title">
                <h2>Available Movie Shows</h2>
            </div>
            
            <!-- Movie Shows Grid -->
            <div class="shows-grid">
                <% if(movieShows != null && !movieShows.isEmpty()) { %>
                    <% for(MovieShow show : movieShows) { %>
                        <div class="show-card">
                            <div class="show-header">
                                <h3 class="movie-name"><%= show.getMovieName() %></h3>
                                <span class="genre-badge"><%= show.getGenre() %></span>
                            </div>
                            
                            <div class="show-details">
                                <div class="detail-item">
                                    <span class="label">Cinema:</span>
                                    <span class="value"><%= show.getCinemaName() %></span>
                                </div>
                                <div class="detail-item">
                                    <span class="label">Screen:</span>
                                    <span class="value"><%= show.getScreenName() %></span>
                                </div>
                                <div class="detail-item">
                                    <span class="label">Date:</span>
                                    <span class="value"><%= show.getShowDate() %></span>
                                </div>
                                <div class="detail-item">
                                    <span class="label">Time:</span>
                                    <span class="value"><%= show.getShowTime() %></span>
                                </div>
                                <div class="detail-item">
                                    <span class="label">Language:</span>
                                    <span class="value"><%= show.getLanguage() %></span>
                                </div>
                                <div class="detail-item">
                                    <span class="label">Available Seats:</span>
                                    <span class="value seats <%= show.getAvailableSeats() < 20 ? "low" : "" %>">
                                        <%= show.getAvailableSeats() %>
                                    </span>
                                </div>
                            </div>
                            
                            <div class="show-footer">
                                <span class="price">₹<%= String.format("%.2f", show.getPrice()) %></span>
                                <% if(show.getAvailableSeats() > 0) { %>
                                    <a href="booking?showId=<%= show.getId() %>" class="btn btn-primary">Book Now</a>
                                <% } else { %>
                                    <span class="btn btn-disabled">Sold Out</span>
                                <% } %>
                            </div>
                        </div>
                    <% } %>
                <% } else { %>
                    <div class="no-shows">
                        <p>No movie shows available at the moment.</p>
                    </div>
                <% } %>
            </div>
        </main>
        
        <!-- Footer -->
        <footer class="footer">
            <p>&copy; 2025 MovieBook. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>