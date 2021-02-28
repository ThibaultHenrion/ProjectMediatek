package com.anicetti.mediatek.persistant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionManager instance;
    private static final String DB_URL = "jdbc:postgresql://149.202.42.98:5434/";
    private static final String DB_NAME = "javaee_dev";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private Connection connection;

    private ConnectionManager() throws SQLException {
        connection = DriverManager.getConnection(DB_URL+DB_NAME, DB_USER, DB_PASSWORD);
    }

    public static ConnectionManager getInstance() {
        if(instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return instance;
    }
}
