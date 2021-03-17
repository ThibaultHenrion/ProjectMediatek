package com.anicetti.mediatek.persistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static final int SIZE = 10;
    private static ConnectionPool instance;
    private static final String DB_URL = "jdbc:postgresql://185.248.33.23:5434/";
    private static final String DB_NAME = "javaee_dev";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "brette";

    private int shift = 0;
    private List<Connection> connections;

    private ConnectionPool() throws SQLException {
        connections = new ArrayList<>();
        for(int i = 0; i < SIZE; i++) {
            connections.add(DriverManager.getConnection(DB_URL+DB_NAME, DB_USER, DB_PASSWORD));
        }
    }

    private static ConnectionPool getInstance() {
        if(instance == null) {
            try {
                instance = new ConnectionPool();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }

    public static Connection getConnection() {
        ConnectionPool instance = getInstance();
        instance.shift++;
        return instance.connections.get(instance.shift % SIZE);
    }
}
