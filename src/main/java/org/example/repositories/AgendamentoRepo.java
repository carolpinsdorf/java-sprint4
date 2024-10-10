package org.example.repositories;

import org.example.entities.Agendamento;
import org.example.entities.Oficina;
import org.example.entities.Carro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepo implements _EntidadeRepo<Agendamento> {
    private final Connection connection;

    public AgendamentoRepo(Connection connection) {
        this.connection = connection;
    }

    public Agendamento findById(int id) { // Altere para 'int'
        Agendamento agendamento = null;
        String sql = "SELECT * FROM T_AGENDAMENTO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                agendamento = new Agendamento(
                        rs.getInt("id"), // Altere para 'getInt'
                        rs.getTimestamp("dthora_agendamento"),
                        rs.getString("status_agendamento"),
                        new Oficina(rs.getInt("fk_oficina")), // Este construtor agora existe
                        new Carro(rs.getInt("fk_carro")) // Assumindo que 'Carro' também tem um construtor que aceita 'int'
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamento;
    }

    @Override
    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM T_AGENDAMENTO";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                        rs.getInt("id"), // Altere para 'getInt'
                        rs.getTimestamp("dthora_agendamento"),
                        rs.getString("status_agendamento"),
                        new Oficina(rs.getInt("fk_oficina")), // Este construtor agora existe
                        new Carro(rs.getInt("fk_carro")) // Assumindo que 'Carro' também tem um construtor que aceita 'int'
                );
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamentos;
    }

    @Override
    public void save(Agendamento entity) {
        String sql = "INSERT INTO T_AGENDAMENTO (dthora_agendamento, status_agendamento, fk_oficina, fk_carro) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(entity.getDthoraAgendamento().getTime()));
            stmt.setString(2, entity.getStatusAgendamento());
            stmt.setInt(3, entity.getOficina().getId());
            stmt.setInt(4, entity.getCarro().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Agendamento entity) {
        String sql = "UPDATE T_AGENDAMENTO SET dthora_agendamento = ?, status_agendamento = ?, fk_oficina = ?, fk_carro = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(entity.getDthoraAgendamento().getTime()));
            stmt.setString(2, entity.getStatusAgendamento());
            stmt.setInt(3, entity.getOficina().getId());
            stmt.setInt(4, entity.getCarro().getId());
            stmt.setInt(5, entity.getId()); // Altere para 'setInt'
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) { // Altere para 'int'
        String sql = "DELETE FROM T_AGENDAMENTO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id); // Altere para 'setInt'
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
