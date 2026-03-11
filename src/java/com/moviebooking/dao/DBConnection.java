package com.moviebooking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/movie_booking_db?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "Fenil";
    private static final String PASSWORD = "Fenil123";  // Change to your MySQL password
    
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            System.err.println("URL: " + URL);
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    // Test connection method
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connection test: SUCCESS");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection test: FAILED");
        return false;
    }
}