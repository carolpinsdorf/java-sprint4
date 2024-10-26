package org.example.repositories;

import org.example.entities.Acesso;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessoRepo implements _EntidadeRepo<Acesso> {

    @Override
    public Acesso findById(int id) {
        Acesso acesso = null;
        String sql = "SELECT * FROM T_ACESSO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                acesso = new Acesso(
                        rs.getInt("id"),
                        rs.getString("email_acesso"),
                        rs.getString("username"),
                        rs.getString("senha")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Tratar a exceção apropriadamente em produção
        }
        return acesso;
    }

    @Override
    public List<Acesso> findAll() {
        List<Acesso> acessos = new ArrayList<>();
        String sql = "SELECT * FROM T_ACESSO";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Acesso acesso = new Acesso(
                        rs.getInt("id"),
                        rs.getString("email_acesso"),
                        rs.getString("username"),
                        rs.getString("senha")
                );
                acessos.add(acesso);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Tratar a exceção apropriadamente em produção
        }
        return acessos;
    }

    @Override
    public void save(Acesso entity) {
        String sql = "INSERT INTO T_ACESSO (email_acesso, username, senha, situacao, data_cadastro) VALUES (?, ?, ?, ?, ?)";

        // Validação da senha
        if (entity.getSenha() == null || entity.getSenha().length() <= 10) {
            throw new IllegalArgumentException("A senha deve ter mais de 10 caracteres.");
        }

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getEmailAcesso());
            stmt.setString(2, entity.getUsername());
            stmt.setString(3, entity.getSenha());
            stmt.setString(4, "ativo"); // Situação inicial
            stmt.setDate(5, new Date(System.currentTimeMillis())); // Data de cadastro atual

            stmt.executeUpdate();
            System.out.println("Acesso salvo com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace(); // Tratar a exceção apropriadamente em produção
        }
    }


    @Override
    public void update(Acesso entity) {
        String sql = "UPDATE T_ACESSO SET email_acesso = ?, username = ?, senha = ?, data_atualizacao = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getEmailAcesso());
            stmt.setString(2, entity.getUsername());
            stmt.setString(3, entity.getSenha());
            stmt.setDate(4, new Date(System.currentTimeMillis())); // Data de atualização atual
            stmt.setInt(5, entity.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Tratar a exceção apropriadamente em produção
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_ACESSO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Tratar a exceção apropriadamente em produção
        }
    }
}

