package com.openjfx.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:economics.db"; // Name of the database

    // Connection to the database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión a SQLite establecida");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // If the database does not exists it is created
    public static void createTable() {
        String sqlTags = "CREATE TABLE IF NOT EXISTS tags (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, color TEXT NOT NULL;";

        String sqlSpends = "CREATE TABLE IF NOT EXISTS spends (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, money REAL NOT NULL, "
                + "quantity INTEGER NOT NULL, date DATE NOT NULL, "
                + "tag_id INTEGER, FOREIGN KEY (tag_id) REFERENCES tags(id));";

        String sqlIncomes = "CREATE TABLE IF NOT EXISTS incomes (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, money REAL NOT NULL, "
                + "date DATE NOT NULL, tag_id INTEGER,"
                + "FOREIGN KEY (tag_id) REFERENCES tags(id));";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // Create the tables
            stmt.execute(sqlTags);
            stmt.execute(sqlSpends);
            stmt.execute(sqlIncomes);

            System.out.println("Tables created or they already exists");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
