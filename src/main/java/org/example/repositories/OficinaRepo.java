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
        String sql = "SELECT * FROM T_OFICINA WHERE id_oficina = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToOficina(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Oficina> findAll() {
        List<Oficina> oficinas = new ArrayList<>();
        String sql = "SELECT * FROM T_OFICINA";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Oficina oficina = mapResultSetToOficina(rs);
                oficinas.add(oficina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oficinas;
    }

    @Override
    public void save(Oficina entity) {
        String sql = "INSERT INTO T_OFICINA (cnpj_oficina, fk_acesso) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getCnpjOficina());
            stmt.setLong(2, entity.getFkAcesso());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Oficina entity) {
        String sql = "UPDATE T_OFICINA SET cnpj_oficina = ?, fk_acesso = ? WHERE id_oficina = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCnpjOficina());
            stmt.setLong(2, entity.getFkAcesso());
            stmt.setLong(3, entity.getIdOficina());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_OFICINA WHERE id_oficina = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Oficina mapResultSetToOficina(ResultSet rs) throws SQLException {
        return new Oficina(
                rs.getLong("id_oficina"),
                rs.getInt("cnpj_oficina"),
                rs.getObject("fk_acesso", Long.class)
        );
    }
}
