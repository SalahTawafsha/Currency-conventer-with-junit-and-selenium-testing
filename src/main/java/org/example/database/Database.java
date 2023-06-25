package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Database {
    private final String host;
    private final String userName;
    private final String password;
    private final String databaseName;

    private Connection connection;

    public Database(String host, String userName, String password, String databaseName) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        connect();
    }

    private void connect() throws RuntimeException {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":3306/" + databaseName + "?autoReconnect=true&useSSL=false", userName,
                    password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to database: " + e.getMessage());
        }
    }

    public Double getRate(String code) throws RuntimeException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT rate FROM currency WHERE code = '" + code + "';");
            if (resultSet == null) {
                throw new Exception("No currency found with code: " + code);
            }
            resultSet.next();

            return resultSet.getDouble("rate");
        } catch (Exception e) {
            throw new RuntimeException("Failed to get rate: " + e.getMessage());
        }
    }


}
