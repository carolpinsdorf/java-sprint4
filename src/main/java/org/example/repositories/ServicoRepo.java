package org.example.repositories;

import org.example.entities.Servico;
import org.example.entities.StatusServico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepo extends _BaseRepoImpl<Servico> {

    public ServicoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Servico findById(Long id) {
        String sql = "SELECT * FROM servicos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Servico(
                            rs.getLong("id"),
                            rs.getString("nome_servico"),
                            rs.getDouble("preco"),
                            StatusServico.valueOf(rs.getString("status"))
                    );
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    @Override
    public List<Servico> findAll() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servicos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getLong("id"),
                        rs.getString("nome_servico"),
                        rs.getDouble("preco"),
                        StatusServico.valueOf(rs.getString("status"))
                );
                servicos.add(servico);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return servicos;
    }

    @Override
    public void save(Servico servico) {
        String sql = "INSERT INTO servicos (nome_servico, preco, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setDouble(2, servico.getPreco());
            stmt.setString(3, servico.getStatus().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void update(Servico servico) {
        String sql = "UPDATE servicos SET nome_servico = ?, preco = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setDouble(2, servico.getPreco());
            stmt.setString(3, servico.getStatus().name());
            stmt.setLong(4, servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM servicos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void handleSQLException(SQLException e) {
        System.err.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    }
}
