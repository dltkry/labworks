package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbFunctions {

    public Connection connectToDb(String dbName, String user, String password) {
        Connection conn = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/" + dbName;
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
}
