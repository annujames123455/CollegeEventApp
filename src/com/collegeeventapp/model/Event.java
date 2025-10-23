package com.collegeeventapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
    private int eventID;
    private String eventName;
    private LocalDate date;
    private LocalTime time;
    private String description;

    public Event(int eventID, String eventName, LocalDate date, LocalTime time, String description) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
