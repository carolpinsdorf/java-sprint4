package org.example.repositories;

import org.example.entities.Oficina;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OficinaRepo implements _EntidadeRepo<Oficina> {

    @Override
    public Oficina findById(int id) {
        Oficina oficina = null;
        String sql = "SELECT * FROM T_OFICINA WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                oficina = mapResultSetToOficina(rs);
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

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
    public void save(Oficina oficina) {
        String sql = "INSERT INTO T_OFICINA (cnpj_oficina, fk_acesso) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, oficina.getCnpjOficina()); // Mude para setLong
            stmt.setInt(2, oficina.getAcesso() != null ? oficina.getAcesso().getId() : null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Oficina oficina) {
        String sql = "UPDATE T_OFICINA SET cnpj_oficina = ?, fk_acesso = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, oficina.getCnpjOficina()); // Mude para setLong
            stmt.setInt(2, oficina.getAcesso() != null ? oficina.getAcesso().getId() : null);
            stmt.setInt(3, oficina.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_OFICINA WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Oficina mapResultSetToOficina(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        long cnpjOficina = rs.getLong("cnpj_oficina"); // Mude para getLong
        int fkAcesso = rs.getInt("fk_acesso");

        Oficina oficina = new Oficina(id, cnpjOficina, null); // Acesso pode ser nulo aqui

        // Se for necessário, carregar a entidade Acesso pelo ID.
        // Exemplo: oficina.setAcesso(new AcessoRepo().findById(fkAcesso));

        return oficina;
    }
}
