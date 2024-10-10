package org.example.repositories;

import org.example.entities.Diagnostico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticoRepo implements _EntidadeRepo<Diagnostico> {
    private Connection connection;

    public DiagnosticoRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Diagnostico findById(int id) {
        Diagnostico diagnostico = null;
        String sql = "SELECT * FROM T_DIAGNOSTICO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                diagnostico = new Diagnostico(
                        rs.getInt("id"), // ID do diagnóstico
                        rs.getString("desc_diagnostico"), // Descrição do diagnóstico
                        null, // Serviço - você pode definir isso conforme necessário
                        null  // Dtc - você pode definir isso conforme necessário
                );
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
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Diagnostico diagnostico = new Diagnostico(
                        rs.getInt("id"),
                        rs.getString("desc_diagnostico"),
                        null, // Serviço - você pode definir isso conforme necessário
                        null  // Dtc - você pode definir isso conforme necessário
                );
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
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getDescDiagnostico());
            stmt.setNull(2, Types.INTEGER); // Substitua isso pelo id do serviço, se necessário
            stmt.setNull(3, Types.INTEGER); // Substitua isso pelo id do dtc, se necessário
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Diagnostico entity) {
        String sql = "UPDATE T_DIAGNOSTICO SET desc_diagnostico = ?, fk_servico = ?, fk_id_dtc = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getDescDiagnostico());
            stmt.setNull(2, Types.INTEGER); // Substitua isso pelo id do serviço, se necessário
            stmt.setNull(3, Types.INTEGER); // Substitua isso pelo id do dtc, se necessário
            stmt.setInt(4, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_DIAGNOSTICO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
