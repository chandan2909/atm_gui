//Handles all database connections and queries.

import java.sql.*;

public class ATMDatabase {
    private Connection conn;

    public ATMDatabase() {
        conn = connectToDatabase();
    }

    private Connection connectToDatabase() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM_System", "root", "236500");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validateUser(String username, String pin) {
        String query = "SELECT user_id FROM users WHERE username = ? AND pin = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public double getBalance(int userId) {
        String query = "SELECT balance FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void depositMoney(int userId, double amount) {
        try {
            String query = "UPDATE users SET balance = balance + ? WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean withdrawMoney(int userId, double amount) {
        if (getBalance(userId) < amount) return false;

        try {
            String query = "UPDATE users SET balance = balance - ? WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getTransactionHistory(int userId) {
        StringBuilder history = new StringBuilder("Transaction History:\n");
        String query = "SELECT transaction_type, amount, timestamp FROM transactions WHERE user_id = ? ORDER BY timestamp DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                history.append(rs.getString("timestamp")).append(" - ")
                        .append(rs.getString("transaction_type")).append(" - ₹")
                        .append(rs.getDouble("amount")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history.toString();
    }
}
