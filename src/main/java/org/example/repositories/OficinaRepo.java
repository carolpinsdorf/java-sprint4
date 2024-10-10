package org.example.repositories;

import org.example.entities.Oficina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OficinaRepo implements _EntidadeRepo<Oficina> {
    private Connection connection;

    public OficinaRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Oficina findById(int id) {
        Oficina oficina = null;
        String sql = "SELECT * FROM T_OFICINA WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                oficina = new Oficina(
                        rs.getInt("id"), // ID da Oficina
                        rs.getInt("cnpj_oficina"), // CNPJ da Oficina
                        null
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
        String sql = "SELECT * FROM T_OFICINA";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Oficina oficina = new Oficina(
                        rs.getInt("id"),
                        rs.getInt("cnpj_oficina"),
                        null
                );
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
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCnpjOficina());
            stmt.setNull(2, Types.INTEGER); // ID do acesso
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Oficina entity) {
        String sql = "UPDATE T_OFICINA SET cnpj_oficina = ?, fk_acesso = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getCnpjOficina());
            stmt.setNull(2, Types.INTEGER); // ID do acesso
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_OFICINA WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
