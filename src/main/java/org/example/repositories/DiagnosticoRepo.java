package org.example.repositories;

import org.example.entities.Diagnostico;
import org.example.entities.Servico;
import org.example.entities.Dtc;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoRepo implements _EntidadeRepo<Diagnostico> {

    @Override
    public Diagnostico findById(int id) {
        Diagnostico diagnostico = null;
        String sql = "SELECT * FROM T_DIAGNOSTICO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                diagnostico = mapResultSetToDiagnostico(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diagnostico;
    }

    @Override
    public List<Diagnostico> findAll() {
        List<Diagnostico> diagnosticos = new ArrayList<>();
        String sql = "SELECT * FROM T_DIAGNOSTICO";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
    public void save(Diagnostico diagnostico) {
        String sql = "INSERT INTO T_DIAGNOSTICO (desc_diagnostico, fk_servico, fk_id_dtc) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, diagnostico.getDescDiagnostico());
            stmt.setInt(2, diagnostico.getServico().getId()); // Presumindo que Servico tem getId()
            stmt.setInt(3, diagnostico.getDtc().getId()); // Presumindo que Dtc tem getId()
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Diagnostico diagnostico) {
        String sql = "UPDATE T_DIAGNOSTICO SET desc_diagnostico = ?, fk_servico = ?, fk_id_dtc = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, diagnostico.getDescDiagnostico());
            stmt.setInt(2, diagnostico.getServico().getId());
            stmt.setInt(3, diagnostico.getDtc().getId());
            stmt.setInt(4, diagnostico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_DIAGNOSTICO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Diagnostico mapResultSetToDiagnostico(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descDiagnostico = rs.getString("desc_diagnostico");

        // Buscar objetos Servico e Dtc pelo ID referenciado (substituir com as lógicas adequadas)
        Servico servico = new Servico(); // Buscar do banco ou criar objeto placeholder
        servico.setId(rs.getInt("fk_servico"));

        Dtc dtc = new Dtc(); // Buscar do banco ou criar objeto placeholder
        dtc.setId(rs.getInt("fk_id_dtc"));

        return new Diagnostico(id, descDiagnostico, servico, dtc);
    }
}
