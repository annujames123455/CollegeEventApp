package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    
    private LoginController loginController;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        super("College Event App - Login");
        this.loginController = new LoginController();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null); // Center the window

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Components
        mainPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        mainPanel.add(emailField);

        mainPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        mainPanel.add(passwordField);

        JButton userLoginButton = new JButton("User Login");
        JButton adminLoginButton = new JButton("Admin Login");
        
        mainPanel.add(userLoginButton);
        mainPanel.add(adminLoginButton);

        add(mainPanel, BorderLayout.CENTER);

        // Action Listeners
        userLoginButton.addActionListener(this::handleUserLogin);
        adminLoginButton.addActionListener(this::handleAdminLogin);
        
        setVisible(true);
    }

    private void handleUserLogin(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (loginController.userLogin(email, password) != null) {
            JOptionPane.showMessageDialog(this, "User Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // NOTE: Replace "test@user.com" with the actual logged-in user's email
            new DashboardFrame(email, false); 
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid User Credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleAdminLogin(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (loginController.adminLogin(email, password) != null) {
            JOptionPane.showMessageDialog(this, "Admin Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new DashboardFrame(email, true); // true for admin role
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Admin Credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}