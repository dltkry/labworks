package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class TaskApp {

    public static void main(String[] args) {
        SpringApplication.run(TaskApp.class, args);

        DbFunctions dbFunctions = new DbFunctions();

        Connection conn = dbFunctions.connectToDb("task_app", "postgres", "qwerty123");

        if (conn != null) {
            System.out.println("Connection to the database was successful.");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
