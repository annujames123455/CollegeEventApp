package com.collegeeventapp.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    // Simple pattern for basic email validation
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // Minimum password length to avoid weak passwords
    private static final int MIN_PASSWORD_LENGTH = 8;

    // Basic strong password policy: at least one upper, lower, digit
    private static final Pattern STRONG_PASSWORD_PATTERN =
        Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    /**
     * Checks if a string is null or empty after trimming whitespace.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Validates an email address format.
     */
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates password strength based on minimum length.
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }
        // Enforce a basic strong policy (upper, lower, digit)
        return STRONG_PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Validates if a string is a valid integer ID.
     */
    public static boolean isValidID(String idStr) {
        if (isNullOrEmpty(idStr)) {
            return false;
        }
        try {
            int id = Integer.parseInt(idStr);
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a string is a valid date in the required format (YYYY-MM-DD).
     */
    public static boolean isValidDate(String dateStr) {
        if (isNullOrEmpty(dateStr)) {
            return false;
        }
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    /**
     * Validates if a string is a valid time in the required format (HH:MM).
     */
    public static boolean isValidTime(String timeStr) {
        if (isNullOrEmpty(timeStr)) {
            return false;
        }
        try {
            LocalTime.parse(timeStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}