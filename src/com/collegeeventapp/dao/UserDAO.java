package com.collegeeventapp.dao;

import com.collegeeventapp.model.User;
import java.sql.*;

public class UserDAO {
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT userID, name, email, phone, passwordHash FROM User WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("userID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("passwordHash")
                    );
                }
                return null;
            }
        }
    }
}
