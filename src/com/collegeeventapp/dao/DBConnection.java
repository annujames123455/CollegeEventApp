package com.collegeeventapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // --- CHANGE THESE TO YOUR ACTUAL DATABASE CREDENTIALS ---
    // The DB_URL format depends on your database type (e.g., mysql, postgresql)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/college_events_db"; 
    private static final String DB_USER = "root"; 
    private static final String DB_PASSWORD = "your_password"; 
    // --------------------------------------------------------
    
    /**
     * Establishes and returns a connection to the database.
     * @return A valid java.sql.Connection object.
     * @throws SQLException if a database access error occurs or the URL is invalid.
     */
    public static Connection getConnection() throws SQLException {
        // DriverManager automatically loads the correct JDBC driver if it's in the classpath
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Safely closes the provided database connection.
     * @param conn The connection object to close.
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Print stack trace if close fails, but do not stop the program
                e.printStackTrace();
            }
        }
    }
}