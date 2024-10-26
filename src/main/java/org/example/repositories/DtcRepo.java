package org.example.repositories;

import org.example.entities.Dtc;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DtcRepo implements _EntidadeRepo<Dtc> {

    @Override
    public Dtc findById(int id) {
        Dtc dtc = null;
        String sql = "SELECT * FROM T_DTC WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dtc = mapResultSetToDtc(rs);
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

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dtc dtc = mapResultSetToDtc(rs);
                dtcs.add(dtc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dtcs;
    }

    @Override
    public void save(Dtc dtc) {
        String sql = "INSERT INTO T_DTC (cod_dtc, descricao) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dtc.getCodDtc());
            stmt.setString(2, dtc.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Dtc dtc) {
        String sql = "UPDATE T_DTC SET cod_dtc = ?, descricao = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dtc.getCodDtc());
            stmt.setString(2, dtc.getDescricao());
            stmt.setInt(3, dtc.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_DTC WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dtc mapResultSetToDtc(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String codDtc = rs.getString("cod_dtc");
        String descricao = rs.getString("descricao");

        return new Dtc(id, codDtc, descricao);
    }
}
