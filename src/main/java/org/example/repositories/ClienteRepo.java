package org.example.repositories;

import org.example.entities.Cliente;
import org.example.entities.Acesso;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepo implements _EntidadeRepo<Cliente> {

    @Override
    public Cliente findById(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM T_CLIENTE WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = mapResultSetToCliente(rs);
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

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = mapResultSetToCliente(rs);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void save(Cliente cliente) {
        String sql = "INSERT INTO T_CLIENTE (cpf_cliente, nm_cliente, dt_nascimento, fk_acesso) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, cliente.getCpfCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
            stmt.setInt(4, cliente.getAcesso().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE T_CLIENTE SET cpf_cliente = ?, nm_cliente = ?, dt_nascimento = ?, fk_acesso = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, cliente.getCpfCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setDate(3, new java.sql.Date(cliente.getDataNascimento().getTime()));
            stmt.setInt(4, cliente.getAcesso().getId());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_CLIENTE WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar erro de forma mais adequada em produção
        }
    }

    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Long cpfCliente = rs.getLong("cpf_cliente");
        String nomeCliente = rs.getString("nm_cliente");
        Date dataNascimento = rs.getDate("dt_nascimento");

        Acesso acesso = new Acesso(); // Aqui você pode buscar o acesso no banco de dados, se necessário


        return new Cliente(id, cpfCliente, nomeCliente, dataNascimento, acesso);
    }
}
