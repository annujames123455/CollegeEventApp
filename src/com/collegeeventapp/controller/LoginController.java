package controller;

import dao.UserDAO;
import dao.AdminDAO;
import model.User;
import model.Admin;
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

            // Simple password check (replace with secure hashing in production)
            if (user.getPasswordHash().equals(password)) { 
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

            // Simple password check (replace with secure hashing in production)
            if (admin.getPasswordHash().equals(password)) { 
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
}