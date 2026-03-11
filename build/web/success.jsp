<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.moviebooking.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Successful - MovieBook</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
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
                <a href="mybookings" class="nav-link">My Bookings</a>
                <a href="logout" class="nav-link logout-btn">Logout</a>
            </div>
        </header>
        
        <!-- Main Content -->
        <main class="main-content">
            <div class="success-container">
                <div class="success-icon">✓</div>
                <h2>Booking Successful!</h2>
                <p class="success-message">Your tickets have been booked successfully.</p>
                
                <div class="booking-summary">
                    <h3>Booking Summary</h3>
                    <div class="summary-details">
                        <div class="summary-row">
                            <span class="label">Movie:</span>
                            <span class="value"><%= request.getAttribute("movieName") %></span>
                        </div>
                        <div class="summary-row">
                            <span class="label">Cinema:</span>
                            <span class="value"><%= request.getAttribute("cinemaName") %></span>
                        </div>
                        <div class="summary-row">
                            <span class="label">Date:</span>
                            <span class="value"><%= request.getAttribute("showDate") %></span>
                        </div>
                        <div class="summary-row">
                            <span class="label">Time:</span>
                            <span class="value"><%= request.getAttribute("showTime") %></span>
                        </div>
                        <div class="summary-row">
                            <span class="label">Seats:</span>
                            <span class="value"><%= request.getAttribute("seats") %></span>
                        </div>
                        <div class="summary-row">
                            <span class="label">Payment Method:</span>
                            <span class="value"><%= request.getAttribute("paymentMethod") %></span>
                        </div>
                        <div class="summary-row total">
                            <span class="label">Total Amount:</span>
                            <span class="value">₹<%= String.format("%.2f", (Double) request.getAttribute("totalAmount")) %></span>
                        </div>
                    </div>
                </div>
                
                <div class="success-actions">
                    <a href="mybookings" class="btn btn-primary">View My Bookings</a>
                    <a href="dashboard" class="btn btn-secondary">Book Another</a>
                </div>
            </div>
        </main>
        
        <!-- Footer -->
        <footer class="footer">
            <p>&copy; 2025 MovieBook. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>