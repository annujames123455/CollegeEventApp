package com.collegeeventapp.dao;

import com.collegeeventapp.model.Admin;
import java.sql.*;

public class AdminDAO {
    public Admin getAdminByEmail(String email) throws SQLException {
        String sql = "SELECT adminID, name, email, passwordHash FROM Admin WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                        rs.getInt("adminID"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("passwordHash")
                    );
                }
                return null;
            }
        }
    }
}
