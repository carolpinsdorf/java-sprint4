package org.example.repositories;

import org.example.entities.Dtc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DtcRepo implements _EntidadeRepo<Dtc> {
    private Connection connection;

    public DtcRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Dtc findById(int id) {
        Dtc dtc = null;
        String sql = "SELECT * FROM T_DTC WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dtc = new Dtc(
                        rs.getInt("id"), // ID do DTC
                        rs.getString("cod_dtc"), // Código do DTC
                        rs.getString("descricao") // Descrição do DTC
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtc;
    }

    @Override
    public List<Dtc> findAll() {
        List<Dtc> dtcs = new ArrayList<>();
        String sql = "SELECT * FROM T_DTC";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Dtc dtc = new Dtc(
                        rs.getInt("id"),
                        rs.getString("cod_dtc"),
                        rs.getString("descricao")
                );
                dtcs.add(dtc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtcs;
    }

    @Override
    public void save(Dtc entity) {
        String sql = "INSERT INTO T_DTC (cod_dtc, descricao) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getCodDtc());
            stmt.setString(2, entity.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Dtc entity) {
        String sql = "UPDATE T_DTC SET cod_dtc = ?, descricao = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getCodDtc());
            stmt.setString(2, entity.getDescricao());
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_DTC WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
