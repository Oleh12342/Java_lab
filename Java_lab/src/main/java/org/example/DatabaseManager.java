package org.example;
import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    public DatabaseManager(String configPath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.password = props.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Помилка зчитування конфігурації: " + e.getMessage());
        }
    }

    public void saveToDb(Clothes c) {
        String sql = "INSERT INTO clothes_inventory (type, name, size, price, material, quantity, length, sleeve_type, has_print, fit) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, c.getClass().getSimpleName());
            pstmt.setString(2, c.getName());
            pstmt.setInt(3, c.getSize());
            pstmt.setDouble(4, c.getPrice());
            pstmt.setString(5, c.getMaterial());
            pstmt.setInt(6, c.getQuantity());

            setSpecificFields(pstmt, c);

            pstmt.executeUpdate();
            System.out.println("Об'єкт збережено в БД!");

        } catch (SQLException e) {
            System.err.println("Помилка БД: " + e.getMessage());
        }
    }

    private void setSpecificFields(PreparedStatement pstmt, Clothes c) throws SQLException {
        pstmt.setNull(7, Types.INTEGER);
        pstmt.setNull(8, Types.VARCHAR);
        pstmt.setNull(9, Types.BOOLEAN);
        pstmt.setNull(10, Types.VARCHAR);

        if (c instanceof Pants p) {
            pstmt.setInt(7, p.getLength());
        }
        if (c instanceof Shirts s) {
            pstmt.setString(8, s.getSleeveType());
        }
        if (c instanceof TShirt t) {
            pstmt.setString(8, t.getSleeveType());
            pstmt.setBoolean(9, t.isHasPrint());
        }
        if (c instanceof Jeans j) {
            pstmt.setInt(7, j.getLength());
            pstmt.setString(10, j.getFit());
        }
    }
}