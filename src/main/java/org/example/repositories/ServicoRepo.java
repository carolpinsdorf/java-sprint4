package org.example.repositories;

import org.example.entities.Servico;
import org.example.entities.StatusServico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepo implements _EntidadeRepo<Servico> {
    private Connection connection;

    public ServicoRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Servico findById(int id) {
        Servico servico = null;
        String sql = "SELECT * FROM T_SERVICO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                servico = new Servico(
                        rs.getInt("id"), // ID do serviço
                        rs.getString("descricao"), // Descrição do serviço
                        rs.getFloat("valor"), // Valor do serviço
                        rs.getString("tempo_execucao"), // Tempo de execução
                        StatusServico.valueOf(rs.getString("status_servico")), // Status do serviço
                        null // Agendamento - você pode definir isso conforme necessário
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servico;
    }

    @Override
    public List<Servico> findAll() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM T_SERVICO";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getFloat("valor"),
                        rs.getString("tempo_execucao"),
                        StatusServico.valueOf(rs.getString("status_servico")), // Status do serviço
                        null
                );
                servicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    @Override
    public void save(Servico entity) {
        String sql = "INSERT INTO T_SERVICO (descricao, valor, tempo_execucao, fk_agendamento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getDescricao());
            stmt.setFloat(2, entity.getValor());
            stmt.setString(3, entity.getTempoExecucao());
            stmt.setNull(4, Types.INTEGER); // ID do agendamento, se necessário
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Servico entity) {
        String sql = "UPDATE T_SERVICO SET descricao = ?, valor = ?, tempo_execucao = ?, fk_agendamento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getDescricao());
            stmt.setFloat(2, entity.getValor());
            stmt.setString(3, entity.getTempoExecucao());
            stmt.setNull(4, Types.INTEGER); // ID do agendamento, se necessário
            stmt.setInt(5, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_SERVICO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
