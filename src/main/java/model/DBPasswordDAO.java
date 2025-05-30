package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBPasswordDAO implements PasswordDAO {
    private Connection connection;

    public DBPasswordDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:passwords.db");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS passwords (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "password TEXT NOT NULL)");
        }
    }

    @Override
    public List<Password> getAllPasswords() {
        List<Password> passwords = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM passwords")) {
            while (rs.next()) {
                passwords.add(new Password(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passwords;
    }

    @Override
    public void addPassword(Password password) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO passwords(name, password) VALUES(?, ?)")) {
            pstmt.setString(1, password.getName());
            pstmt.setString(2, password.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(Password password) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE passwords SET name = ?, password = ? WHERE id = ?")) {
            pstmt.setString(1, password.getName());
            pstmt.setString(2, password.getPassword());
            pstmt.setInt(3, password.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePassword(int id) {
        try (PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM passwords WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getType() {
        return "DATABASE";
    }
}