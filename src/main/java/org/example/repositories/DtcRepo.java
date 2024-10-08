package org.example.repositories;

import org.example.entities.Dtc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DtcRepo extends _BaseRepoImpl<Dtc> {

    public DtcRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Dtc findById(Long id) {
        String sql = "SELECT * FROM T_DTC WHERE id_dtc = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDtc(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dtc> findAll() {
        List<Dtc> dtcs = new ArrayList<>();
        String sql = "SELECT * FROM T_DTC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
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
    public void save(Dtc entity) {
        String sql = "INSERT INTO T_DTC (cod_dtc, descricao) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getCodDtc());
            stmt.setString(2, entity.getDescricao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Dtc entity) {
        String sql = "UPDATE T_DTC SET cod_dtc = ?, descricao = ? WHERE id_dtc = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getCodDtc());
            stmt.setString(2, entity.getDescricao());
            stmt.setLong(3, entity.getIdDtc());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_DTC WHERE id_dtc = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dtc mapResultSetToDtc(ResultSet rs) throws SQLException {
        return new Dtc(
                rs.getLong("id_dtc"),
                rs.getString("cod_dtc"),
                rs.getString("descricao")
        );
    }
}
