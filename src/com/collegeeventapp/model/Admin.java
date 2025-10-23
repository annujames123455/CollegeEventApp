package com.collegeeventapp.model;

public class Admin {
    private int adminID;
    private String name;
    private String email;
    private String passwordHash;

    public Admin(int adminID, String name, String email, String passwordHash) {
        this.adminID = adminID;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public int getAdminID() { return adminID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
