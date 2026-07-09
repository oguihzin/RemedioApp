package com.example.remedioapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RemedioRepository {

    public void salvar(Remedio remedio) {
        String sql = "INSERT INTO remedio (nome, dosagem, horarios) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseHelper.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, remedio.getNome());
            stmt.setString(2, remedio.getDosagem());
            stmt.setString(3, String.join(",", remedio.getHorarios()));

            stmt.executeUpdate();
            System.out.println("Remédio salvo com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar remédio: " + e.getMessage());
        }
    }

    public List<Remedio> listarTodos() {
        List<Remedio> remedios = new java.util.ArrayList<>();
        String sql = "SELECT * FROM remedio";

        try (Connection conn = DatabaseHelper.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String dosagem = rs.getString("dosagem");
                List<String> horarios = List.of(rs.getString("horarios").split(","));

                remedios.add(new Remedio(id, nome, dosagem, horarios));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar remédios: " + e.getMessage());
        }

        return remedios;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM remedio WHERE id = ?";

        try (Connection conn = DatabaseHelper.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Remédio removido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover remédio: " + e.getMessage());
        }
    }

    public Remedio buscarPorId(int id) {
    String sql = "SELECT * FROM remedio WHERE id = ?";

    try (Connection conn = DatabaseHelper.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        java.sql.ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int rid = rs.getInt("id");
            String nome = rs.getString("nome");
            String dosagem = rs.getString("dosagem");
            List<String> horarios = List.of(rs.getString("horarios").split(","));
            return new Remedio(rid, nome, dosagem, horarios);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar remédio: " + e.getMessage());
    }

    return null;
    }
}