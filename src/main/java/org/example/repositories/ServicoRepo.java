package org.example.repositories;

import org.example.entities.Agendamento;
import org.example.entities.Servico;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepo implements _EntidadeRepo<Servico> {

    @Override
    public Servico findById(int id) {
        Servico servico = null;
        String sql = "SELECT * FROM T_SERVICO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servico = mapRowToServico(rs);
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

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Servico servico = mapRowToServico(rs);
                servicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    @Override
    public void save(Servico servico) {
        String sql = "INSERT INTO T_SERVICO (descricao, valor, tempo_execucao, fk_agendamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getDescricao());
            stmt.setFloat(2, servico.getValor());
            stmt.setString(3, servico.getTempoExecucao());
            if (servico.getAgendamento() != null) {
                stmt.setInt(4, servico.getAgendamento().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Servico servico) {
        String sql = "UPDATE T_SERVICO SET descricao = ?, valor = ?, tempo_execucao = ?, fk_agendamento = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getDescricao());
            stmt.setFloat(2, servico.getValor());
            stmt.setString(3, servico.getTempoExecucao());
            if (servico.getAgendamento() != null) {
                stmt.setInt(4, servico.getAgendamento().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setInt(5, servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_SERVICO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Servico mapRowToServico(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String descricao = resultSet.getString("descricao");
        float valor = resultSet.getFloat("valor");
        String tempoExecucao = resultSet.getString("tempo_execucao");
        int fkAgendamento = resultSet.getInt("fk_agendamento");

        // Aqui você pode instanciar Agendamento se precisar dele.
        Agendamento agendamento = null;
        if (!resultSet.wasNull()) {
            agendamento = new AgendamentoRepo().findById(fkAgendamento); // Carregar Agendamento pelo ID
        }

        return new Servico(id, descricao, valor, tempoExecucao, null, agendamento);
    }
}
