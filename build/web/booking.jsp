<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.moviebooking.model.MovieShow" %>
<%@ page import="com.moviebooking.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Tickets - MovieBook</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        MovieShow show = (MovieShow) request.getAttribute("movieShow");
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
            <div class="booking-container">
                <div class="booking-header">
                    <a href="dashboard" class="back-link">← Back to Shows</a>
                    <h2>Book Tickets</h2>
                </div>
                
                <% if(request.getAttribute("error") != null) { %>
                    <div class="alert alert-error">
                        <%= request.getAttribute("error") %>
                    </div>
                <% } %>
                
                <div class="booking-content">
                    <!-- Movie Details -->
                    <div class="movie-details-card">
                        <h3>Movie Details</h3>
                        <div class="movie-info">
                            <div class="info-row">
                                <span class="info-label">Movie:</span>
                                <span class="info-value"><%= show.getMovieName() %></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Cinema:</span>
                                <span class="info-value"><%= show.getCinemaName() %></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Screen:</span>
                                <span class="info-value"><%= show.getScreenName() %></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Date:</span>
                                <span class="info-value"><%= show.getShowDate() %></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Time:</span>
                                <span class="info-value"><%= show.getShowTime() %></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Price per Ticket:</span>
                                <span class="info-value price">₹<%= String.format("%.2f", show.getPrice()) %></span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">Available Seats:</span>
                                <span class="info-value"><%= show.getAvailableSeats() %></span>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Booking Form -->
                    <div class="booking-form-card">
                        <h3>Booking Details</h3>
                        <form action="booking" method="POST" id="bookingForm">
                            <input type="hidden" name="showId" value="<%= show.getId() %>">
                            
                            <div class="form-group">
                                <label for="seats">Number of Seats:</label>
                                <select id="seats" name="seats" onchange="updateTotal()">
                                    <% for(int i = 1; i <= Math.min(10, show.getAvailableSeats()); i++) { %>
                                        <option value="<%= i %>"><%= i %></option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <div class="total-section">
                                <span class="total-label">Total Amount:</span>
                                <span class="total-amount" id="totalAmount">
                                    ₹<%= String.format("%.2f", show.getPrice()) %>
                                </span>
                            </div>
                            
                            <div class="payment-section">
                                <h4>Select Payment Method</h4>
                                
                                <div class="payment-options">
                                    <label class="payment-option">
                                        <input type="radio" name="paymentMethod" value="UPI" 
                                               onchange="showPaymentForm('upi')" checked>
                                        <span class="option-text">UPI</span>
                                    </label>
                                    
                                    <label class="payment-option">
                                        <input type="radio" name="paymentMethod" value="CREDIT_CARD" 
                                               onchange="showPaymentForm('card')">
                                        <span class="option-text">Credit Card</span>
                                    </label>
                                </div>
                                
                                <!-- UPI Payment Form -->
                                <div id="upiForm" class="payment-form active">
                                    <div class="form-group">
                                        <label for="upiId">UPI ID:</label>
                                        <input type="text" id="upiId" name="upiId" 
                                               placeholder="yourname@upi" required>
                                    </div>
                                </div>
                                
                                <!-- Credit Card Payment Form -->
                                <div id="cardForm" class="payment-form">
                                    <div class="form-group">
                                        <label for="cardNumber">Card Number:</label>
                                        <input type="text" id="cardNumber" name="cardNumber" 
                                               placeholder="1234 5678 9012 3456" maxlength="19">
                                    </div>
                                    <div class="form-group">
                                        <label for="cardHolder">Card Holder Name:</label>
                                        <input type="text" id="cardHolder" name="cardHolder" 
                                               placeholder="John Doe">
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group half">
                                            <label for="expiry">Expiry (MM/YY):</label>
                                            <input type="text" id="expiry" name="expiry" 
                                                   placeholder="12/25" maxlength="5">
                                        </div>
                                        <div class="form-group half">
                                            <label for="cvv">CVV:</label>
                                            <input type="password" id="cvv" name="cvv" 
                                                   placeholder="123" maxlength="3">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <button type="submit" class="btn btn-primary btn-large">
                                Confirm & Pay
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        
        <!-- Footer -->
        <footer class="footer">
            <p>&copy; 2025 MovieBook. All rights reserved.</p>
        </footer>
    </div>
    
    <script>
        const ticketPrice = <%= show.getPrice() %>;
        
        function updateTotal() {
            const seats = document.getElementById('seats').value;
            const total = ticketPrice * seats;
            document.getElementById('totalAmount').textContent = '₹' + total.toFixed(2);
        }
        
        function showPaymentForm(type) {
            document.getElementById('upiForm').classList.remove('active');
            document.getElementById('cardForm').classList.remove('active');
            
            if (type === 'upi') {
                document.getElementById('upiForm').classList.add('active');
                document.getElementById('upiId').required = true;
                document.getElementById('cardNumber').required = false;
            } else {
                document.getElementById('cardForm').classList.add('active');
                document.getElementById('upiId').required = false;
                document.getElementById('cardNumber').required = true;
            }
        }
    </script>
</body>
</html>