package org.example.repositories;

import org.example.entities.Oficina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OficinaRepo extends _BaseRepoImpl<Oficina> {

    public OficinaRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Oficina findById(Long id) {
        Oficina oficina = null;
        try {
            String sql = "SELECT * FROM oficinas WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                oficina = new Oficina(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oficina;
    }

    @Override
    public List<Oficina> findAll() {
        List<Oficina> oficinas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM oficinas";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Oficina oficina = new Oficina(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj"),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                );
                oficinas.add(oficina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oficinas;
    }

    @Override
    public void save(Oficina oficina) {
        try {
            String sql = "INSERT INTO oficinas (nome, cnpj, telefone, endereco) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, oficina.getNome());
            stmt.setString(2, oficina.getCnpj());
            stmt.setString(3, oficina.getTelefone());
            stmt.setString(4, oficina.getEndereco());
            stmt.executeUpdate();

            // Obtenha o ID gerado
            String selectSql = "SELECT OFICINAS_SEQ.CURRVAL FROM dual";
            PreparedStatement selectStmt = connection.prepareStatement(selectSql);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                oficina.setId(rs.getLong(1)); // Atribui o ID gerado à oficina
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Oficina oficina) {
        try {
            String sql = "UPDATE oficinas SET nome = ?, cnpj = ?, telefone = ?, endereco = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, oficina.getNome());
            stmt.setString(2, oficina.getCnpj());
            stmt.setString(3, oficina.getTelefone());
            stmt.setString(4, oficina.getEndereco());
            stmt.setLong(5, oficina.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM oficinas WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
