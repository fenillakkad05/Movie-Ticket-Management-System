package com.moviebooking.dao;

import com.moviebooking.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    public boolean createBooking(Booking booking) {
        String query = "INSERT INTO book (user_id, show_id, seats, total_amount, payment_method) " +
                       "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getShowId());
            pstmt.setInt(3, booking.getSeats());
            pstmt.setDouble(4, booking.getTotalAmount());
            pstmt.setString(5, booking.getPaymentMethod());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Booking> getUserBookings(int userId) {
        List<Booking> bookings = new ArrayList<>();
        
        String query = "SELECT b.*, m.name as movie_name, c.name as cinema_name, " +
                       "s.screen_name, ms.show_time, ms.show_date " +
                       "FROM book b " +
                       "JOIN movie_shows ms ON b.show_id = ms.id " +
                       "JOIN movie m ON ms.movie_id = m.id " +
                       "JOIN screen s ON ms.screen_id = s.id " +
                       "JOIN cinema c ON s.cinema_id = c.id " +
                       "WHERE b.user_id = ? " +
                       "ORDER BY b.booking_date DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setShowId(rs.getInt("show_id"));
                booking.setSeats(rs.getInt("seats"));
                booking.setTotalAmount(rs.getDouble("total_amount"));
                booking.setPaymentMethod(rs.getString("payment_method"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                booking.setStatus(rs.getString("status"));
                booking.setMovieName(rs.getString("movie_name"));
                booking.setCinemaName(rs.getString("cinema_name"));
                booking.setScreenName(rs.getString("screen_name"));
                booking.setShowTime(rs.getString("show_time"));
                booking.setShowDate(rs.getString("show_date"));
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
}