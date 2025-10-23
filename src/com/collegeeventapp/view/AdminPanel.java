package com.collegeeventapp.view;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    public AdminPanel() {
        super(new BorderLayout(10, 10));
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Create Event", new RegisterEventPanel());
        tabs.addTab("All Events", new EventListPanel());
        add(tabs, BorderLayout.CENTER);
    }
}
