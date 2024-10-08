package org.example.repositories;

import org.example.entities.Diagnostico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoRepo extends _BaseRepoImpl<Diagnostico> {

    public DiagnosticoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Diagnostico findById(Long id) {
        String sql = "SELECT * FROM T_DIAGNOSTICO WHERE id_diagnostico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDiagnostico(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Diagnostico> findAll() {
        List<Diagnostico> diagnosticos = new ArrayList<>();
        String sql = "SELECT * FROM T_DIAGNOSTICO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Diagnostico diagnostico = mapResultSetToDiagnostico(rs);
                diagnosticos.add(diagnostico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosticos;
    }

    @Override
    public void save(Diagnostico entity) {
        String sql = "INSERT INTO T_DIAGNOSTICO (desc_diagnostico, fk_servico, fk_id_dtc) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getDescDiagnostico());
            stmt.setLong(2, entity.getFkServico());
            stmt.setLong(3, entity.getFkDtc());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Diagnostico entity) {
        String sql = "UPDATE T_DIAGNOSTICO SET desc_diagnostico = ?, fk_servico = ?, fk_id_dtc = ? WHERE id_diagnostico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getDescDiagnostico());
            stmt.setLong(2, entity.getFkServico());
            stmt.setLong(3, entity.getFkDtc());
            stmt.setLong(4, entity.getIdDiagnostico());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_DIAGNOSTICO WHERE id_diagnostico = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Diagnostico mapResultSetToDiagnostico(ResultSet rs) throws SQLException {
        return new Diagnostico(
                rs.getLong("id_diagnostico"),
                rs.getString("desc_diagnostico"),
                rs.getLong("fk_servico"),
                rs.getLong("fk_id_dtc")
        );
    }
}
