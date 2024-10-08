package org.example.repositories;

import org.example.entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepo extends _BaseRepoImpl<Cliente> {

    public ClienteRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Cliente findById(Long id) {
        String sql = "SELECT * FROM T_CLIENTE WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM T_CLIENTE";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
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
    public void save(Cliente entity) {
        String sql = "INSERT INTO T_CLIENTE (cpf_cliente, nm_cliente, rg_cliente, dt_nascimento, sx_cliente, estado_civil, fk_acesso) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, entity.getCpfCliente());
            stmt.setString(2, entity.getNomeCliente());
            stmt.setString(3, entity.getRgCliente());
            stmt.setDate(4, new Date(entity.getDataNascimento().getTime()));
            stmt.setString(5, entity.getSexoCliente());
            stmt.setString(6, entity.getEstadoCivil());
            stmt.setLong(7, entity.getFkAcesso());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cliente entity) {
        String sql = "UPDATE T_CLIENTE SET cpf_cliente = ?, nm_cliente = ?, rg_cliente = ?, dt_nascimento = ?, sx_cliente = ?, estado_civil = ?, fk_acesso = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, entity.getCpfCliente());
            stmt.setString(2, entity.getNomeCliente());
            stmt.setString(3, entity.getRgCliente());
            stmt.setDate(4, new Date(entity.getDataNascimento().getTime()));
            stmt.setString(5, entity.getSexoCliente());
            stmt.setString(6, entity.getEstadoCivil());
            stmt.setLong(7, entity.getFkAcesso());
            stmt.setLong(8, entity.getIdCliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_CLIENTE WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getLong("id_cliente"),
                rs.getLong("cpf_cliente"),
                rs.getString("nm_cliente"),
                rs.getString("rg_cliente"),
                rs.getDate("dt_nascimento"),
                rs.getString("sx_cliente"),
                rs.getString("estado_civil"),
                rs.getLong("fk_acesso")
        );
    }
}
