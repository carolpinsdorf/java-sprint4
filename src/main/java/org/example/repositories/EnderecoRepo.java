package org.example.repositories;

import org.example.entities.Endereco;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepo implements _EntidadeRepo<Endereco> {

    @Override
    public Endereco findById(int id) {
        Endereco endereco = null;
        String sql = "SELECT * FROM T_ENDERECO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                endereco = mapResultSetToEndereco(rs);
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

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Endereco endereco = mapResultSetToEndereco(rs);
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecos;
    }

    @Override
    public void save(Endereco endereco) {
        String sql = "INSERT INTO T_ENDERECO (cep_endereco, log_endereco, num_endereco, bairro, cidade, estado, fk_cliente, fk_oficina) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, endereco.getCepEndereco());
            stmt.setString(2, endereco.getLogEndereco());
            stmt.setInt(3, endereco.getNumEndereco());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setInt(7, endereco.getCliente() != null ? endereco.getCliente().getId() : null);
            stmt.setInt(8, endereco.getOficina() != null ? endereco.getOficina().getId() : null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Endereco endereco) {
        String sql = "UPDATE T_ENDERECO SET cep_endereco = ?, log_endereco = ?, num_endereco = ?, bairro = ?, cidade = ?, estado = ?, fk_cliente = ?, fk_oficina = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, endereco.getCepEndereco());
            stmt.setString(2, endereco.getLogEndereco());
            stmt.setInt(3, endereco.getNumEndereco());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getCidade());
            stmt.setString(6, endereco.getEstado());
            stmt.setInt(7, endereco.getCliente() != null ? endereco.getCliente().getId() : null);
            stmt.setInt(8, endereco.getOficina() != null ? endereco.getOficina().getId() : null);
            stmt.setInt(9, endereco.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_ENDERECO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Endereco mapResultSetToEndereco(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int cepEndereco = rs.getInt("cep_endereco");
        String logEndereco = rs.getString("log_endereco");
        int numEndereco = rs.getInt("num_endereco");
        String bairro = rs.getString("bairro");
        String cidade = rs.getString("cidade");
        String estado = rs.getString("estado");

        Endereco endereco = new Endereco(id, cepEndereco, logEndereco, numEndereco, bairro, cidade, estado, null, null);

        return endereco;
    }
}
