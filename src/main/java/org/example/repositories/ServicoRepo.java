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
        Servico servico = null;
        try {
            String sql = "SELECT * FROM servicos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servico = new Servico(
                        rs.getLong("id"),
                        rs.getString("nome_servico"),
                        rs.getDouble("preco"),
                        StatusServico.valueOf(rs.getString("status")) // Mapeia o status do banco
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
        try {
            String sql = "SELECT * FROM servicos";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getLong("id"),
                        rs.getString("nome_servico"),
                        rs.getDouble("preco"),
                        StatusServico.valueOf(rs.getString("status")) // Mapeia o status do banco
                );
                servicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    @Override
    public void save(Servico servico) {
        try {
            String sql = "INSERT INTO servicos (nome_servico, preco, status) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getNomeServico());
            stmt.setDouble(2, servico.getPreco());
            stmt.setString(3, servico.getStatus().name()); // Salva o status como string
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Servico servico) {
        try {
            String sql = "UPDATE servicos SET nome_servico = ?, preco = ?, status = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getNomeServico());
            stmt.setDouble(2, servico.getPreco());
            stmt.setString(3, servico.getStatus().name()); // Atualiza o status como string
            stmt.setLong(4, servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM servicos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
