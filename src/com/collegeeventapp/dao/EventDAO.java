package com.collegeeventapp.dao;

import com.collegeeventapp.model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    /**
     * Inserts a new Event record into the database (Creation).
     */
    public void addEvent(Event event) throws SQLException {
        String sql = "INSERT INTO Event (eventID, eventName, date, time, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, event.getEventID());
            stmt.setString(2, event.getEventName());
            // Convert Java 8 LocalDate/LocalTime to SQL types
            stmt.setDate(3, java.sql.Date.valueOf(event.getDate()));
            stmt.setTime(4, java.sql.Time.valueOf(event.getTime()));
            stmt.setString(5, event.getDescription());
            
            stmt.executeUpdate();
            System.out.println("Event added successfully: " + event.getEventName());
        }
    }

    /**
     * Retrieves all Events from the database (Read - List).
     */
    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT eventID, eventName, date, time, description FROM Event ORDER BY date, time";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                // Map database results back to the Event model object
                Event event = new Event(
                    rs.getInt("eventID"),
                    rs.getString("eventName"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime(),
                    rs.getString("description")
                );
                events.add(event);
            }
        }
        return events;
    }

    /**
     * Retrieves a single Event by its ID (Read - Single).
     */
    public Event getEventById(int eventID) throws SQLException {
        String sql = "SELECT eventID, eventName, date, time, description FROM Event WHERE eventID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, eventID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Event(
                        rs.getInt("eventID"),
                        rs.getString("eventName"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getString("description")
                    );
                }
            }
        }
        return null;
    }
    
    /**
     * Updates an existing Event's details (Update).
     */
    public void updateEvent(Event event) throws SQLException {
        String sql = "UPDATE Event SET eventName = ?, date = ?, time = ?, description = ? WHERE eventID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, event.getEventName());
            stmt.setDate(2, java.sql.Date.valueOf(event.getDate()));
            stmt.setTime(3, java.sql.Time.valueOf(event.getTime()));
            stmt.setString(4, event.getDescription());
            stmt.setInt(5, event.getEventID());
            
            stmt.executeUpdate();
            System.out.println("Event updated successfully: " + event.getEventName());
        }
    }

    /**
     * Deletes an Event by ID (Delete).
     */
    public void deleteEvent(int eventId) throws SQLException {
        String sql = "DELETE FROM Event WHERE eventID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, eventId);
            
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Event " + eventId + " deleted successfully.");
            } else {
                System.out.println("No event found with ID " + eventId + " to delete.");
            }
        }
    }
}
