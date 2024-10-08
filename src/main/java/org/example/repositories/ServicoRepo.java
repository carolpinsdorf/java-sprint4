package org.example.repositories;

import org.example.entities.Servico;
import org.example.entities.StatusServico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepo extends _BaseRepoImpl<Servico> {

    public ServicoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Servico findById(Long id) {
        String sql = "SELECT * FROM T_SERVICO WHERE id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToServico(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Servico> findAll() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM T_SERVICO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Servico servico = mapResultSetToServico(rs);
                servicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    @Override
    public void save(Servico entity) {
        String sql = "INSERT INTO T_SERVICO (descricao, valor, tempo_execucao, status_servico, fk_agendamento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getDescricao());
            stmt.setFloat(2, entity.getValor());
            stmt.setString(3, entity.getTempoExecucao());
            stmt.setString(4, entity.getStatusServico().name());  // Armazenando o nome do enum
            stmt.setLong(5, entity.getFkAgendamento());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Servico entity) {
        String sql = "UPDATE T_SERVICO SET descricao = ?, valor = ?, tempo_execucao = ?, status_servico = ?, fk_agendamento = ? WHERE id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getDescricao());
            stmt.setFloat(2, entity.getValor());
            stmt.setString(3, entity.getTempoExecucao());
            stmt.setString(4, entity.getStatusServico().name());  // Atualizando o status como enum
            stmt.setLong(5, entity.getFkAgendamento());
            stmt.setLong(6, entity.getIdServico());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_SERVICO WHERE id_servico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Servico mapResultSetToServico(ResultSet rs) throws SQLException {
        return new Servico(
                rs.getLong("id_servico"),
                rs.getString("descricao"),
                rs.getFloat("valor"),
                rs.getString("tempo_execucao"),
                StatusServico.valueOf(rs.getString("status_servico")),  // Convertendo a string de volta para o enum
                rs.getLong("fk_agendamento")
        );
    }
}
