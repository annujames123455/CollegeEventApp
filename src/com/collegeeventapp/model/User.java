package com.collegeeventapp.model;

public class User {
    private int userID; // Replaced studentID
    private String name;
    private String email;
    private String phone;
    private String passwordHash; // Added for security/login

    // Constructor
    public User(int userID, String name, String email, String phone, String passwordHash) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
    }

    // Getters
    public int getUserID() { return userID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPasswordHash() { return passwordHash; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
