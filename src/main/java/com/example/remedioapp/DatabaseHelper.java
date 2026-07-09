package com.example.remedioapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:remedios.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void inicializarBanco() {
        String sqlRemedio = """
            CREATE TABLE IF NOT EXISTS remedio (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                dosagem TEXT,
                horarios TEXT NOT NULL
            );
        """;

        String sqlRegistro = """
            CREATE TABLE IF NOT EXISTS registro_tomada (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                remedio_id INTEGER NOT NULL,
                data TEXT NOT NULL,
                horario_previsto TEXT NOT NULL,
                tomado INTEGER NOT NULL DEFAULT 0,
                horario_real TEXT,
                FOREIGN KEY (remedio_id) REFERENCES remedio(id)
            );
        """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlRemedio);
            stmt.execute(sqlRegistro);
            System.out.println("Banco de dados inicializado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar banco: " + e.getMessage());
        }
    }
}