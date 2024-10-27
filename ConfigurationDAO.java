package com.chatbot.dao;

import com.chatbot.model.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAO {
    public void addConfiguration(Configuration config) {
        String sql = "INSERT INTO Configurations (config_key, config_value) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, config.getKey());
            pstmt.setString(2, config.getValue());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfigurationById(Long id) {
        String sql = "SELECT * FROM Configurations WHERE id = ?";
        Configuration config = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                config = new Configuration();
                config.setId(rs.getLong("id"));
                config.setKey(rs.getString("config_key"));
                config.setValue(rs.getString("config_value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return config;
    }

   
}
