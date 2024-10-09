package org.example.repositories;

import org.example.entities.Agendamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendamentoRepo extends _BaseRepoImpl<Agendamento> {

    public AgendamentoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Agendamento findById(Long id) {
        String query = "SELECT * FROM agendamentos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Agendamento(
                        rs.getLong("id"),
                        rs.getDate("dataAgendada"),
                        rs.getString("descricao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String query = "SELECT * FROM agendamentos";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                agendamentos.add(new Agendamento(
                        rs.getLong("id"),
                        rs.getDate("dataAgendada"),
                        rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamentos;
    }

    @Override
    public void save(Agendamento agendamento) {
        String query = "INSERT INTO agendamentos (id, dataAgendada, descricao) VALUES (AGENDAMENTOS_SEQ.NEXTVAL, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(agendamento.getDataAgendada().getTime()));
            stmt.setString(2, agendamento.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Agendamento agendamento) {
        String query = "UPDATE agendamentos SET dataAgendada = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(agendamento.getDataAgendada().getTime()));
            stmt.setString(2, agendamento.getDescricao());
            stmt.setLong(3, agendamento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM agendamentos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
