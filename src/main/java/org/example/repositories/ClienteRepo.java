package org.example.repositories;

import org.example.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepo implements _EntidadeRepo<Cliente> {
    private Connection connection;

    public ClienteRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Cliente findById(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM T_CLIENTE WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"), // ID do cliente
                        Long.valueOf(rs.getInt("cpf_cliente")), // Converter CPF de int para Long
                        rs.getString("nm_cliente"), // Nome do cliente
                        rs.getDate("dt_nascimento"), // Data de nascimento
                        null // Acesso - você pode definir isso conforme necessário
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM T_CLIENTE";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        Long.valueOf(rs.getInt("cpf_cliente")), // Converter CPF de int para Long
                        rs.getString("nm_cliente"),
                        rs.getDate("dt_nascimento"),
                        null // Acesso - você pode definir isso conforme necessário
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void save(Cliente entity) {
        String sql = "INSERT INTO T_CLIENTE (cpf_cliente, nm_cliente, dt_nascimento, sx_cliente, estado_civil, fk_acesso) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCpfCliente().intValue()); // Converter Long para int
            stmt.setString(2, entity.getNomeCliente());
            stmt.setDate(3, new java.sql.Date(entity.getDataNascimento().getTime())); // Converta Date para java.sql.Date
            stmt.setNull(4, Types.VARCHAR); // Substitua isso pelo gênero, se necessário
            stmt.setNull(5, Types.VARCHAR); // Substitua isso pelo estado civil, se necessário
            stmt.setNull(6, Types.INTEGER); // Substitua isso pelo id do acesso, se necessário
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cliente entity) {
        String sql = "UPDATE T_CLIENTE SET cpf_cliente = ?, nm_cliente = ?, dt_nascimento = ?, sx_cliente = ?, estado_civil = ?, fk_acesso = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCpfCliente().intValue()); // Converter Long para int
            stmt.setString(2, entity.getNomeCliente());
            stmt.setDate(3, new java.sql.Date(entity.getDataNascimento().getTime())); // Converta Date para java.sql.Date
            stmt.setNull(4, Types.VARCHAR); // Substitua isso pelo gênero, se necessário
            stmt.setNull(5, Types.VARCHAR); // Substitua isso pelo estado civil, se necessário
            stmt.setNull(6, Types.INTEGER); // Substitua isso pelo id do acesso, se necessário
            stmt.setInt(7, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_CLIENTE WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
