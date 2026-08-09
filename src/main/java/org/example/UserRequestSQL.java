package org.example;

public class UserRequestSQL {
    public static final String createTable =
            """
            CREATE TABLE IF NOT EXISTS User (
            id serial primary key auto_increment,
            name varchar,
            email varchar,
            age INTEGER CHECK (age > 18)
            )
            """;
}
