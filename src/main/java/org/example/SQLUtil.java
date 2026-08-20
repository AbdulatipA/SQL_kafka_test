package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtil {
    private final String JDBC_URL;
    private final String JDBC_USERNAME;
    private final String JDBC_PASSWORD;

    public SQLUtil(String JDBC_URL, String JDBC_USERNAME, String JDBC_PASSWORD) {
        this.JDBC_URL = JDBC_URL;
        this.JDBC_USERNAME = JDBC_USERNAME;
        this.JDBC_PASSWORD = JDBC_PASSWORD;
    }


    public String sendSQLRequest(String sql) {
        try(
                Connection connection = getConnection();
                var statement = connection.createStatement();
        ) {
            boolean hasResultSet = statement.execute(sql);

            if (hasResultSet) {
                try(var resultSet = statement.getResultSet()) {
                    if (resultSet.next()) {
                        return resultSet.getString(1);
                    }
                }
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
