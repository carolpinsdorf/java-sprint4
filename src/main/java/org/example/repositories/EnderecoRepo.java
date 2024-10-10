package org.example.repositories;

import org.example.entities.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepo implements _EntidadeRepo<Endereco> {
    private Connection connection;

    public EnderecoRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Endereco findById(int id) {
        Endereco endereco = null;
        String sql = "SELECT * FROM T_ENDERECO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                endereco = new Endereco(
                        rs.getInt("id"), // ID do Endereço
                        rs.getInt("cep_endereco"), // CEP
                        rs.getString("log_endereco"), // Logradouro
                        rs.getInt("num_endereco"), // Número
                        rs.getString("bairro"), // Bairro
                        rs.getString("cidade"), // Cidade
                        rs.getString("estado"), // Estado
                        null, // Cliente - você pode definir isso conforme necessário
                        null // Oficina - você pode definir isso conforme necessário
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
        String sql = "SELECT * FROM T_ENDERECO";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("id"),
                        rs.getInt("cep_endereco"),
                        rs.getString("log_endereco"),
                        rs.getInt("num_endereco"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        null, // Cliente - você pode definir isso conforme necessário
                        null // Oficina - você pode definir isso conforme necessário
                );
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecos;
    }

    @Override
    public void save(Endereco entity) {
        String sql = "INSERT INTO T_ENDERECO (cep_endereco, log_endereco, num_endereco, cmpl_endereco, bairro, cidade, estado, fk_cliente, fk_oficina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCepEndereco());
            stmt.setString(2, entity.getLogEndereco());
            stmt.setInt(3, entity.getNumEndereco());
            stmt.setNull(4, Types.VARCHAR); // Complemento do endereço
            stmt.setString(5, entity.getBairro());
            stmt.setString(6, entity.getCidade());
            stmt.setString(7, entity.getEstado());
            stmt.setNull(8, Types.INTEGER); // ID do cliente
            stmt.setNull(9, Types.INTEGER); // ID da oficina
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Endereco entity) {
        String sql = "UPDATE T_ENDERECO SET cep_endereco = ?, log_endereco = ?, num_endereco = ?, cmpl_endereco = ?, bairro = ?, cidade = ?, estado = ?, fk_cliente = ?, fk_oficina = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCepEndereco());
            stmt.setString(2, entity.getLogEndereco());
            stmt.setInt(3, entity.getNumEndereco());
            stmt.setNull(4, Types.VARCHAR); // Complemento do endereço
            stmt.setString(5, entity.getBairro());
            stmt.setString(6, entity.getCidade());
            stmt.setString(7, entity.getEstado());
            stmt.setNull(8, Types.INTEGER); // ID do cliente
            stmt.setNull(9, Types.INTEGER); // ID da oficina
            stmt.setInt(10, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_ENDERECO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
