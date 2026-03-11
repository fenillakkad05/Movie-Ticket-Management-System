<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.moviebooking.model.Booking" %>
<%@ page import="com.moviebooking.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Bookings - MovieBook</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    %>
    
    <div class="dashboard-container">
        <!-- Header -->
        <header class="header">
            <div class="header-left">
                <h1>🎬 MovieBook</h1>
            </div>
            <div class="header-right">
                <span class="welcome-text">Welcome, <%= user.getName() %></span>
                <a href="dashboard" class="nav-link">Dashboard</a>
                <a href="logout" class="nav-link logout-btn">Logout</a>
            </div>
        </header>
        
        <!-- Main Content -->
        <main class="main-content">
            <div class="page-title">
                <h2>My Bookings</h2>
                <a href="dashboard" class="btn btn-secondary">Book New</a>
            </div>
            
            <div class="bookings-container">
                <% if(bookings != null && !bookings.isEmpty()) { %>
                    <table class="bookings-table">
                        <thead>
                            <tr>
                                <th>Booking ID</th>
                                <th>Movie</th>
                                <th>Cinema</th>
                                <th>Screen</th>
                                <th>Date & Time</th>
                                <th>Seats</th>
                                <th>Amount</th>
                                <th>Payment</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Booking booking : bookings) { %>
                                <tr>
                                    <td>#<%= booking.getId() %></td>
                                    <td><%= booking.getMovieName() %></td>
                                    <td><%= booking.getCinemaName() %></td>
                                    <td><%= booking.getScreenName() %></td>
                                    <td>
                                        <%= booking.getShowDate() %><br>
                                        <small><%= booking.getShowTime() %></small>
                                    </td>
                                    <td><%= booking.getSeats() %></td>
                                    <td>₹<%= String.format("%.2f", booking.getTotalAmount()) %></td>
                                    <td><%= booking.getPaymentMethod() %></td>
                                    <td>
                                        <span class="status-badge <%= booking.getStatus().toLowerCase() %>">
                                            <%= booking.getStatus() %>
                                        </span>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } else { %>
                    <div class="no-bookings">
                        <p>You haven't made any bookings yet.</p>
                        <a href="dashboard" class="btn btn-primary">Browse Movies</a>
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