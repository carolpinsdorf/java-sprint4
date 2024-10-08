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
        String sql = "SELECT * FROM T_ENDERECO WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEndereco(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Endereco> findAll() {
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM T_ENDERECO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
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
    public void save(Endereco entity) {
        String sql = "INSERT INTO T_ENDERECO (cep_endereco, log_endereco, num_endereco, cmpl_endereco, bairro, cidade, estado, fk_cliente, fk_oficina) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getCepEndereco());
            stmt.setString(2, entity.getLogEndereco());
            stmt.setInt(3, entity.getNumEndereco());
            stmt.setString(4, entity.getCmplEndereco());
            stmt.setString(5, entity.getBairro());
            stmt.setString(6, entity.getCidade());
            stmt.setString(7, entity.getEstado());
            stmt.setObject(8, entity.getFkCliente(), Types.BIGINT);
            stmt.setObject(9, entity.getFkOficina(), Types.BIGINT);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Endereco entity) {
        String sql = "UPDATE T_ENDERECO SET cep_endereco = ?, log_endereco = ?, num_endereco = ?, cmpl_endereco = ?, bairro = ?, cidade = ?, estado = ?, fk_cliente = ?, fk_oficina = ? WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCepEndereco());
            stmt.setString(2, entity.getLogEndereco());
            stmt.setInt(3, entity.getNumEndereco());
            stmt.setString(4, entity.getCmplEndereco());
            stmt.setString(5, entity.getBairro());
            stmt.setString(6, entity.getCidade());
            stmt.setString(7, entity.getEstado());
            stmt.setObject(8, entity.getFkCliente(), Types.BIGINT);
            stmt.setObject(9, entity.getFkOficina(), Types.BIGINT);
            stmt.setLong(10, entity.getIdEndereco());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_ENDERECO WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Endereco mapResultSetToEndereco(ResultSet rs) throws SQLException {
        return new Endereco(
                rs.getLong("id_endereco"),
                rs.getInt("cep_endereco"),
                rs.getString("log_endereco"),
                rs.getInt("num_endereco"),
                rs.getString("cmpl_endereco"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getObject("fk_cliente", Long.class),
                rs.getObject("fk_oficina", Long.class)
        );
    }
}
