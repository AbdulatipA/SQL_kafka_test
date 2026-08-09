package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtil {

    public void sendSQLRequest(String sql) {
        try(Connection connection = getConnection()) {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    ENVUtil.getEnvByName("JDBC_URL"),
                    ENVUtil.getEnvByName("JDBC_USERNAME"),
                    ENVUtil.getEnvByName("JDBC_PASSWORD")
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
