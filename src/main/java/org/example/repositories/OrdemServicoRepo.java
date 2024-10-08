package org.example.repositories;

import org.example.entities.OrdemServico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepo extends _BaseRepoImpl<OrdemServico> {

    public OrdemServicoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public OrdemServico findById(Long id) {
        String sql = "SELECT * FROM T_ORDEM_SERVICO WHERE id_ordem_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToOrdemServico(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrdemServico> findAll() {
        List<OrdemServico> ordens = new ArrayList<>();
        String sql = "SELECT * FROM T_ORDEM_SERVICO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrdemServico ordem = mapResultSetToOrdemServico(rs);
                ordens.add(ordem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordens;
    }

    @Override
    public void save(OrdemServico entity) {
        String sql = "INSERT INTO T_ORDEM_SERVICO (status_servico, fk_agendamento) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getStatusServico());
            stmt.setLong(2, entity.getFkAgendamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OrdemServico entity) {
        String sql = "UPDATE T_ORDEM_SERVICO SET status_servico = ?, fk_agendamento = ? WHERE id_ordem_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getStatusServico());
            stmt.setLong(2, entity.getFkAgendamento());
            stmt.setLong(3, entity.getIdOrdemServico());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_ORDEM_SERVICO WHERE id_ordem_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrdemServico mapResultSetToOrdemServico(ResultSet rs) throws SQLException {
        return new OrdemServico(
                rs.getLong("id_ordem_servico"),
                rs.getString("status_servico"),
                rs.getLong("fk_agendamento")
        );
    }
}
