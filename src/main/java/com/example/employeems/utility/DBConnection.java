package com.example.employeems.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/employee_ms";
    private static final String USER = "postgres";
    private static final String PASSWORD = "270811OLC0802023";

    // Method to establish a new connection for each request
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver"); // Load PostgreSQL JDBC Driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();
        }
        return null;
    }
}
