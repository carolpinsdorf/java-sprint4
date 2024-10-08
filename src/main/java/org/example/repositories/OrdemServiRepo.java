package org.example.repositories;

import org.example.entities.OrdemServico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServiRepo extends _BaseRepoImpl<OrdemServico> {

    public OrdemServiRepo(Connection connection) {
        super(connection);
    }

    @Override
    public OrdemServico findById(Long id) {
        OrdemServico ordemServico = null;
        try {
            String sql = "SELECT * FROM ordens_servico WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ordemServico = new OrdemServico(
                        rs.getLong("id"),
                        rs.getString("descricaoServico"),
                        rs.getDouble("valorTotal")
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
        try {
            String sql = "SELECT * FROM ordens_servico";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrdemServico ordemServico = new OrdemServico(
                        rs.getLong("id"),
                        rs.getString("descricaoServico"),
                        rs.getDouble("valorTotal")
                );
                ordensServico.add(ordemServico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordensServico;
    }

    @Override
    public void save(OrdemServico ordemServico) {
        try {
            String sql = "INSERT INTO ordens_servico (descricaoServico, valorTotal) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ordemServico.getDescricaoServico());
            stmt.setDouble(2, ordemServico.getValorTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(OrdemServico ordemServico) {
        try {
            String sql = "UPDATE ordens_servico SET descricaoServico = ?, valorTotal = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, ordemServico.getDescricaoServico());
            stmt.setDouble(2, ordemServico.getValorTotal());
            stmt.setLong(3, ordemServico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM ordens_servico WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
