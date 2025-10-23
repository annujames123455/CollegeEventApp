package com.collegeeventapp.view;

import com.collegeeventapp.dao.EventDAO;
import com.collegeeventapp.model.Event;
import com.collegeeventapp.util.Validator;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegisterEventPanel extends JPanel {

    private final JTextField idField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JTextField dateField = new JTextField("2025-12-31");
    private final JTextField timeField = new JTextField("12:00");
    private final JTextArea descArea = new JTextArea(3, 20);

    private final EventDAO eventDAO = new EventDAO();

    public RegisterEventPanel() {
        super(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(5, 2, 8, 8));
        form.add(new JLabel("Event ID:"));
        form.add(idField);
        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Date (YYYY-MM-DD):"));
        form.add(dateField);
        form.add(new JLabel("Time (HH:MM):"));
        form.add(timeField);
        form.add(new JLabel("Description:"));
        form.add(new JScrollPane(descArea));

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(e -> onAddEvent());

        add(form, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }

    private void onAddEvent() {
        String idStr = idField.getText();
        String name = nameField.getText();
        String dateStr = dateField.getText();
        String timeStr = timeField.getText();
        String desc = descArea.getText();

        if (!Validator.isValidID(idStr)) {
            showError("Invalid Event ID.");
            return;
        }
        if (Validator.isNullOrEmpty(name)) {
            showError("Name is required.");
            return;
        }
        if (!Validator.isValidDate(dateStr)) {
            showError("Invalid date format.");
            return;
        }
        if (!Validator.isValidTime(timeStr)) {
            showError("Invalid time format.");
            return;
        }

        Event ev = new Event(
            Integer.parseInt(idStr),
            name,
            LocalDate.parse(dateStr),
            LocalTime.parse(timeStr),
            desc
        );
        try {
            eventDAO.addEvent(ev);
            JOptionPane.showMessageDialog(this, "Event added.");
        } catch (SQLException ex) {
            showError("Failed to add: " + ex.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
