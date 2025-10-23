package com.collegeeventapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // Database connection details (use environment variables when available)
    private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/college_events_db";
    private static final String DEFAULT_DB_USER = "root";
    private static final String DEFAULT_DB_PASSWORD = "";

    private static String envOrDefault(String key, String fallback) {
        String value = System.getenv(key);
        return (value == null || value.isEmpty()) ? fallback : value;
    }
    
    /**
     * Establishes and returns a connection to the database.
     * @return A valid java.sql.Connection object.
     * @throws SQLException if a database access error occurs or the URL is invalid.
     */
    public static Connection getConnection() throws SQLException {
        // Resolve from environment or fall back to defaults
        String url = envOrDefault("DB_URL", DEFAULT_DB_URL);
        String user = envOrDefault("DB_USER", DEFAULT_DB_USER);
        String password = envOrDefault("DB_PASSWORD", DEFAULT_DB_PASSWORD);
        return DriverManager.getConnection(url, user, password);
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