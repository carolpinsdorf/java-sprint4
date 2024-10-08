package org.example.repositories;

import org.example.entities.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepo extends _BaseRepoImpl<Endereco> {

    public EnderecoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Endereco findById(Long id) {
        Endereco endereco = null;
        try {
            String sql = "SELECT * FROM enderecos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                endereco = new Endereco(
                        rs.getLong("id"),
                        rs.getString("rua"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep"),
                        rs.getString("numero")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    @Override
    public List<Endereco> findAll() {
        List<Endereco> enderecos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM enderecos";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getLong("id"),
                        rs.getString("rua"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep"),
                        rs.getString("numero")
                );
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    @Override
    public void save(Endereco endereco) {
        try {
            String sql = "INSERT INTO enderecos (rua, cidade, estado, cep, numero) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getNumero());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Endereco endereco) {
        try {
            String sql = "UPDATE enderecos SET rua = ?, cidade = ?, estado = ?, cep = ?, numero = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getCidade());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getNumero());
            stmt.setLong(6, endereco.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM enderecos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
