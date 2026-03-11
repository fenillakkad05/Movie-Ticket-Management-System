🎬 Movie Ticket Management System

A Java Web Application for booking movie tickets online.
This project is developed using Java Servlet, JSP, JDBC, and MySQL and follows DAO and Strategy Design Patterns to maintain a clean and modular architecture.


📌 Project Overview

The Movie Ticket Management System allows users to browse movies, select show timings, book tickets, and view booking history.
The application provides secure login, seat booking functionality, and payment processing using different payment strategies.

This project demonstrates the implementation of Java Web Technologies and Design Patterns in a real-world application.


🚀 Features

User Registration and Login
Movie Listing
Show Timing Selection
Online Ticket Booking
Payment Processing
Booking Confirmation
View Booking History
Secure Session Management
Authentication Filter for protected pages


🛠 Technologies Used
Java
Servlet
JSP
JDBC
MySQL
HTML
CSS
Apache Tomcat
NetBeans IDE


🧩 Design Patterns Used

1️⃣ DAO (Data Access Object) Pattern
Used for database interaction and separation of business logic from database logic.

DAO Classes:
UserDAO
BookingDAO
MovieShowDAO


2️⃣ Strategy Pattern
Used to implement different payment methods.
Payment Strategies:
Credit Card Payment
UPI Payment

🗂 Project Structure
Movie-Ticket-Management-System
│
├── src
│   ├── dao
│   ├── model
│   ├── servlet
│   ├── filter
│   └── pattern
│
├── web
│   ├── index.html
│   ├── signup.jsp
│   ├── dashboard.jsp
│   ├── booking.jsp
│   ├── mybookings.jsp
│   └── success.jsp
│
├── nbproject
└── build


🗄 Database Tables
The system uses the following database tables:

USER
MOVIE
CINEMA
SCREEN
MOVIE_SHOWS
BOOK
