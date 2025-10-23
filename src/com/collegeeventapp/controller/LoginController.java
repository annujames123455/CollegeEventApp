package com.collegeeventapp.controller;

import com.collegeeventapp.dao.UserDAO;
import com.collegeeventapp.dao.AdminDAO;
import com.collegeeventapp.model.User;
import com.collegeeventapp.model.Admin;

=======
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;

public class LoginController {
    
    private UserDAO userDAO;
    private AdminDAO adminDAO;

    public LoginController() {
        // Initialize DAO layer dependencies
        this.userDAO = new UserDAO();
        // Assuming AdminDAO exists to handle Admin authentication logic
        this.adminDAO = new AdminDAO(); 
    }

    /**
     * Authenticates a standard user (student) against the database.
     * * NOTE: In a production app, the password comparison MUST use a secure 
     * hashing library (like jBCrypt) and NOT direct string equality.
     * * @param email The user's email.
     * @param password The raw password to verify.
     * @return The logged-in User object, or null if login fails.
     */
    public User userLogin(String email, String password) {
        try {
            User user = userDAO.getUserByEmail(email);
            
            if (user == null) {
                System.out.println("User Login Failed: User not found.");
                return null;
            }

            // Verify password securely (supports legacy plain and "$sha256$" hashed passwords)
            if (verifyPassword(password, user.getPasswordHash())) {
                System.out.println("User " + user.getName() + " logged in successfully.");
                return user;
            } else {
                System.out.println("User Login Failed: Incorrect password.");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Database error during user login: " + e.getMessage());
            return null;
        }
    }

    /**
     * Authenticates an administrator against the database.
     * @param email The admin's email.
     * @param password The raw password to verify.
     * @return The logged-in Admin object, or null if login fails.
     */
    public Admin adminLogin(String email, String password) {
        try {
            Admin admin = adminDAO.getAdminByEmail(email);
            
            if (admin == null) {
                System.out.println("Admin Login Failed: Admin not found.");
                return null;
            }

            // Verify password securely (supports legacy plain and "$sha256$" hashed passwords)
            if (verifyPassword(password, admin.getPasswordHash())) {
                System.out.println("Admin " + admin.getName() + " logged in successfully.");
                return admin;
            } else {
                System.out.println("Admin Login Failed: Incorrect password.");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Database error during admin login: " + e.getMessage());
            return null;
        }
    }

    // --- Security helpers ---
    private static boolean verifyPassword(String rawPassword, String storedValue) {
        if (storedValue == null || rawPassword == null) {
            return false;
        }
        // If stored as "$sha256$<hex>", compute SHA-256 of raw and compare constant-time
        final String prefix = "$sha256$";
        if (storedValue.startsWith(prefix)) {
            String expectedHex = storedValue.substring(prefix.length());
            String actualHex = sha256Hex(rawPassword);
            return constantTimeEquals(expectedHex, actualHex);
        }
        // Fallback: legacy plain-text compare, but constant-time to reduce timing leaks
        return constantTimeEquals(storedValue, rawPassword);
    }

    private static String sha256Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Should never happen on standard JVMs; treat as verification failure
            return "";
        }
    }

    private static boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        int lenA = a.length();
        int lenB = b.length();
        int max = Math.max(lenA, lenB);
        int result = 0;
        for (int i = 0; i < max; i++) {
            char chA = i < lenA ? a.charAt(i) : 0;
            char chB = i < lenB ? b.charAt(i) : 0;
            result |= chA ^ chB;
        }
        return result == 0 && lenA == lenB;
    }
}