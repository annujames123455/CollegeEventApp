package com.collegeeventapp.view;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame(String email, boolean isAdmin) {
        super("College Event App - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Events", new EventListPanel());

        if (isAdmin) {
            tabs.addTab("Admin", new AdminPanel());
        }

        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }
}
