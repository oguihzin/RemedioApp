package com.example.remedioapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RegistroTomadaRepository {

    public void salvar(RegistroTomada registro) {
        String sql = "INSERT INTO registro_tomada (remedio_id, data, horario_previsto, tomado, horario_real) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseHelper.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, registro.getRemedioId());
            stmt.setString(2, registro.getData().toString());
            stmt.setString(3, registro.getHorarioPrevisto().toString());
            stmt.setInt(4, registro.isTomado() ? 1 : 0);
            stmt.setString(5, registro.getHorarioReal() != null ? registro.getHorarioReal().toString() : null);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar registro: " + e.getMessage());
        }
    }

    public List<RegistroTomada> listarPorData(LocalDate data) {
        List<RegistroTomada> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro_tomada WHERE data = ?";

        try (Connection conn = DatabaseHelper.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                registros.add(mapear(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar registros: " + e.getMessage());
        }

        return registros;
    }

    public void marcarComoTomado(int id, LocalTime horarioReal) {
        String sql = "UPDATE registro_tomada SET tomado = 1, horario_real = ? WHERE id = ?";

        try (Connection conn = DatabaseHelper.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, horarioReal.toString());
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao marcar como tomado: " + e.getMessage());
        }
    }

    private RegistroTomada mapear(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int remedioId = rs.getInt("remedio_id");
        LocalDate data = LocalDate.parse(rs.getString("data"));
        LocalTime horarioPrevisto = LocalTime.parse(rs.getString("horario_previsto"));
        boolean tomado = rs.getInt("tomado") == 1;
        String horarioRealStr = rs.getString("horario_real");
        LocalTime horarioReal = horarioRealStr != null ? LocalTime.parse(horarioRealStr) : null;

        return new RegistroTomada(id, remedioId, data, horarioPrevisto, tomado, horarioReal);
    }
}