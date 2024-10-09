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
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("cpf")
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
        String sql = "SELECT * FROM clientes";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("cpf")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void save(Cliente cliente) {
        // Verifica se já existe um cliente com o mesmo CPF
        String checkQuery = "SELECT COUNT(*) FROM clientes WHERE cpf = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setString(1, cliente.getCpf());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Cliente com o CPF " + cliente.getCpf() + " já existe!");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Obtém o próximo ID da sequência
        Long novoId = null;
        String idQuery = "SELECT CLIENTES_SEQ.NEXTVAL FROM dual";
        try (PreparedStatement idStmt = connection.prepareStatement(idQuery);
             ResultSet idRs = idStmt.executeQuery()) {
            if (idRs.next()) {
                novoId = idRs.getLong(1);
            } else {
                throw new SQLException("Não foi possível gerar um novo ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Insere o cliente usando o novo ID
        String query = "INSERT INTO clientes (id, nome, telefone, cpf) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, novoId); // Usar o ID gerado manualmente
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getCpf());
            stmt.executeUpdate();

            cliente.setId(novoId);
            System.out.println("Cliente inserido: " + cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, cpf = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getCpf());
            stmt.setLong(4, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
