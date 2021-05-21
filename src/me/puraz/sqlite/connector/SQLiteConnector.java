package me.puraz.sqlite.connector;

import java.sql.*;

public class SQLiteConnector {
    
    public static void connectdb(String dbName) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                if(conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    public static void createTable(String dbName) {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbName); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String name, double capacity) {
        String sql = "INSERT INTO users(name,capacity) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readTable(String dbName) {
        String sql = "SELECT id, name, capacity FROM users";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
             Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql);){

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
