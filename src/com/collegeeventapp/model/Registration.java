package com.collegeeventapp.model;

import java.time.LocalDateTime;

public class Registration {
    private int registrationID; // Primary Key
    private int userID;         // Foreign key to User
    private int eventID;        // Foreign key to Event
    private LocalDateTime registrationDate; 

    // Constructor
    public Registration(int registrationID, int userID, int eventID, LocalDateTime registrationDate) {
        this.registrationID = registrationID;
        this.userID = userID;
        this.eventID = eventID;
        this.registrationDate = registrationDate;
    }

    // Getters
    public int getRegistrationID() { return registrationID; }
    public int getUserID() { return userID; }
    public int getEventID() { return eventID; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }

    // Setters (if needed for updates, though registration records are often immutable)
    public void setUserID(int userID) { this.userID = userID; }
    public void setEventID(int eventID) { this.eventID = eventID; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }
}