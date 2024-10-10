package org.example.repositories;

import org.example.entities.OrdemServico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepo implements _EntidadeRepo<OrdemServico> {
    private Connection connection;

    public OrdemServicoRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public OrdemServico findById(int id) {
        OrdemServico ordemServico = null;
        String sql = "SELECT * FROM T_ORDEM_SERVICO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ordemServico = new OrdemServico(
                        rs.getInt("id"), // ID da Ordem de Serviço
                        rs.getString("status_servico"), // Status da Ordem de Serviço
                        null // Agendamento - você pode definir isso conforme necessário
                );
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
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                OrdemServico ordemServico = new OrdemServico(
                        rs.getInt("id"),
                        rs.getString("status_servico"),
                        null // Agendamento - você pode definir isso conforme necessário
                );
                ordensServico.add(ordemServico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordensServico;
    }

    @Override
    public void save(OrdemServico entity) {
        String sql = "INSERT INTO T_ORDEM_SERVICO (status_servico, fk_agendamento) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getStatusServico());
            stmt.setNull(2, Types.INTEGER); // ID do agendamento
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OrdemServico entity) {
        String sql = "UPDATE T_ORDEM_SERVICO SET status_servico = ?, fk_agendamento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getStatusServico());
            stmt.setNull(2, Types.INTEGER); // ID do agendamento
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_ORDEM_SERVICO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
