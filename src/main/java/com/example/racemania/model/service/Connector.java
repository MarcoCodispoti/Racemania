package com.example.racemania.model.service;  //inizio codice ChatGPT

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connector instance = null;
    private Connection conn = null;

    private Connector() throws SQLException {
        initializeConnection();
    }

    private void initializeConnection() throws SQLException {
        try{
            InputStream input = Connector.class.getResourceAsStream("/com/example/racemania/db.properties");
        if (input == null) {
            throw new SQLException("File db.properties non trovato");
        }
        Properties properties = new Properties();
        properties.load(input);

            String connectionUrl = properties.getProperty("CONNECTION_URL");
            String user = properties.getProperty("LOGIN_USER");
            String pass = properties.getProperty("LOGIN_PASS");

            conn = DriverManager.getConnection(connectionUrl, user, pass);
        } catch (Exception e) {
            throw new SQLException("Errore nella creazione della connessione", e);
        }
    }

    public static Connector getInstance() throws SQLException {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            initializeConnection();
        }
        return conn;
    }

}

