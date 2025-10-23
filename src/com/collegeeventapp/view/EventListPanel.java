package com.collegeeventapp.view;

import com.collegeeventapp.dao.EventDAO;
import com.collegeeventapp.model.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EventListPanel extends JPanel {

    private final JTable table;
    private final DefaultTableModel model;
    private final EventDAO eventDAO;

    public EventListPanel() {
        super(new BorderLayout(10, 10));
        this.eventDAO = new EventDAO();

        model = new DefaultTableModel(new Object[]{"ID", "Name", "Date", "Time", "Description"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadEvents());
        add(refreshButton, BorderLayout.SOUTH);

        loadEvents();
    }

    private void loadEvents() {
        model.setRowCount(0);
        try {
            List<Event> events = eventDAO.getAllEvents();
            DateTimeFormatter dateFmt = DateTimeFormatter.ISO_LOCAL_DATE;
            DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm");
            for (Event ev : events) {
                model.addRow(new Object[]{
                    ev.getEventID(),
                    ev.getEventName(),
                    dateFmt.format(ev.getDate()),
                    timeFmt.format(ev.getTime()),
                    ev.getDescription()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load events: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
