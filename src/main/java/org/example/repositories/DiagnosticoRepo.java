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
        Diagnostico diagnostico = null;
        try {
            String sql = "SELECT * FROM diagnosticos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                diagnostico = new Diagnostico(
                        rs.getLong("id"),
                        rs.getString("descricaoProblema"),
                        rs.getString("solucaoProposta"),
                        rs.getDouble("custoEstimado")
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
        try {
            String sql = "SELECT * FROM diagnosticos";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Diagnostico diagnostico = new Diagnostico(
                        rs.getLong("id"),
                        rs.getString("descricaoProblema"),
                        rs.getString("solucaoProposta"),
                        rs.getDouble("custoEstimado")
                );
                diagnosticos.add(diagnostico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnosticos;
    }

    @Override
    public void save(Diagnostico diagnostico) {
        try {
            String sql = "INSERT INTO diagnosticos (id, descricaoProblema, solucaoProposta, custoEstimado) " +
                    "VALUES (DIAGNOSTICOS_SEQ.NEXTVAL, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, diagnostico.getDescricaoProblema());
            stmt.setString(2, diagnostico.getSolucaoProposta());
            stmt.setDouble(3, diagnostico.getCustoEstimado());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Diagnostico diagnostico) {
        try {
            String sql = "UPDATE diagnosticos SET descricaoProblema = ?, solucaoProposta = ?, custoEstimado = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, diagnostico.getDescricaoProblema());
            stmt.setString(2, diagnostico.getSolucaoProposta());
            stmt.setDouble(3, diagnostico.getCustoEstimado());
            stmt.setLong(4, diagnostico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM diagnosticos WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
