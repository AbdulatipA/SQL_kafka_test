package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtil {
    private final String JDBC_URL;
    private final String JDBC_PASSWORD;
    private final String JDBC_USERNAME;

    public SQLUtil(String JDBC_URL, String JDBC_PASSWORD, String JDBC_USERNAME) {
        this.JDBC_URL = JDBC_URL;
        this.JDBC_PASSWORD = JDBC_PASSWORD;
        this.JDBC_USERNAME = JDBC_USERNAME;
    }


    public void sendSQLRequest(String sql) {
        try(Connection connection = getConnection()) {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    JDBC_URL, JDBC_PASSWORD, JDBC_USERNAME
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
