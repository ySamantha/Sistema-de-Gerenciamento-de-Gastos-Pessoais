package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:gastos.db");
        connection.createStatement().execute(
            "CREATE TABLE IF NOT EXISTS despesas (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "valor REAL," +
            "categoria TEXT," +
            "descricao TEXT)"
        );
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
