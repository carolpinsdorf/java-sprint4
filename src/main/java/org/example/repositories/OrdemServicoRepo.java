package org.example.repositories;

import org.example.entities.OrdemServico;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepo implements _EntidadeRepo<OrdemServico> {

    @Override
    public OrdemServico findById(int id) {
        OrdemServico ordemServico = null;
        String sql = "SELECT * FROM T_ORDEM_SERVICO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ordemServico = mapResultSetToOrdemServico(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordemServico;
    }

    @Override
    public List<OrdemServico> findAll() {
        List<OrdemServico> ordensServico = new ArrayList<>();
        String sql = "SELECT * FROM T_ORDEM_SERVICO";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OrdemServico ordemServico = mapResultSetToOrdemServico(rs);
                ordensServico.add(ordemServico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordensServico;
    }

    @Override
    public void save(OrdemServico ordemServico) {
        String sql = "INSERT INTO T_ORDEM_SERVICO (status_servico, fk_agendamento) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ordemServico.getStatusServico());
            stmt.setInt(2, ordemServico.getAgendamento() != null ? ordemServico.getAgendamento().getId() : null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OrdemServico ordemServico) {
        String sql = "UPDATE T_ORDEM_SERVICO SET status_servico = ?, fk_agendamento = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ordemServico.getStatusServico());
            stmt.setInt(2, ordemServico.getAgendamento() != null ? ordemServico.getAgendamento().getId() : null);
            stmt.setInt(3, ordemServico.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_ORDEM_SERVICO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrdemServico mapResultSetToOrdemServico(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String statusServico = rs.getString("status_servico");
        int fkAgendamento = rs.getInt("fk_agendamento");

        OrdemServico ordemServico = new OrdemServico(id, statusServico, null);

        return ordemServico;
    }
}
