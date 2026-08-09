package org.example;

public class UserRequestSQL {
    private UserRequestSQL() {
    }

    public static final String createTable =
            """
            CREATE TABLE IF NOT EXISTS Users (
            id SERIAL PRIMARY KEY,
            name varchar,
            email varchar,
            age INTEGER CHECK (age > 18)
            )
            """;


    public static String insertUserTable(String name, String email, int age) {
        return """
               INSERT INTO users (name, email, age)
                VALUES ('%s', '%s', %d)
             """.formatted(name, email, age);
    }


    public static String getUserNameTable(String name) {
        return """
                   SELECT name FROM Users WHERE name = '%s'
                   """.formatted(name);
    }
}
