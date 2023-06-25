package org.example.database;

import java.sql.Connection;

public class ConnectToDatabase {
    private final String host;
    private final String userName;
    private final String password;
    private final String databaseName;

    private Connection connection;

    public ConnectToDatabase(String host, String userName, String password, String databaseName) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + host + "/" + databaseName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", userName, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double getRate(String code) {
        try {
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT rate FROM currency WHERE code = '" + code + "';");
            resultSet.next();
            return resultSet.getDouble("rate");
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

}
