package org.example.repositories;

import org.example.entities.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepo extends _BaseRepoImpl<Agendamento> {

    public AgendamentoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Agendamento findById(Long id) {
        String sql = "SELECT * FROM T_AGENDAMENTO WHERE id_agendamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAgendamento(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM T_AGENDAMENTO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Agendamento agendamento = mapResultSetToAgendamento(rs);
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamentos;
    }

    @Override
    public void save(Agendamento entity) {
        String sql = "INSERT INTO T_AGENDAMENTO (dthora_agendamento, status_agendamento, obs_agendamento, fk_oficina, fk_carro) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, new Timestamp(entity.getDthoraAgendamento().getTime()));
            stmt.setString(2, entity.getStatusAgendamento());
            stmt.setString(3, entity.getObsAgendamento());
            stmt.setLong(4, entity.getFkOficina());
            stmt.setLong(5, entity.getFkCarro());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Agendamento entity) {
        String sql = "UPDATE T_AGENDAMENTO SET dthora_agendamento = ?, status_agendamento = ?, obs_agendamento = ?, fk_oficina = ?, fk_carro = ? WHERE id_agendamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(entity.getDthoraAgendamento().getTime()));
            stmt.setString(2, entity.getStatusAgendamento());
            stmt.setString(3, entity.getObsAgendamento());
            stmt.setLong(4, entity.getFkOficina());
            stmt.setLong(5, entity.getFkCarro());
            stmt.setLong(6, entity.getIdAgendamento());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_AGENDAMENTO WHERE id_agendamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Agendamento mapResultSetToAgendamento(ResultSet rs) throws SQLException {
        return new Agendamento(
                rs.getLong("id_agendamento"),
                rs.getTimestamp("dthora_agendamento"),
                rs.getString("status_agendamento"),
                rs.getString("obs_agendamento"),
                rs.getLong("fk_oficina"),
                rs.getLong("fk_carro")
        );
    }
}
