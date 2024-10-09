package org.example.repositories;

import org.example.entities.Acesso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessoRepo extends _BaseRepoImpl<Acesso> {

    public AcessoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Acesso findById(Long id) {
        String sql = "SELECT * FROM T_ACESSO WHERE id_acesso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAcesso(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<Acesso> findAll() {
        List<Acesso> acessos = new ArrayList<>();
        String sql = "SELECT * FROM T_ACESSO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Acesso acesso = mapResultSetToAcesso(rs);
                acessos.add(acesso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Você pode querer lançar uma exceção personalizada aqui
        }
        return acessos;
    }

    @Override
    public void save(Acesso entity) {
        String sql = "INSERT INTO T_ACESSO (email_acesso, username, senha, situacao, data_cadastro) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getEmailAcesso());
            stmt.setString(2, entity.getUsername());
            stmt.setString(3, entity.getSenha());
            stmt.setString(4, entity.getSituacao());
            stmt.setDate(5, new java.sql.Date(entity.getDataCadastro().getTime()));
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Criar Acesso falhou, nenhuma linha afetada.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setIdAcesso(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Criar Acesso falhou, nenhum ID obtido.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Acesso entity) {
        String sql = "UPDATE T_ACESSO SET email_acesso = ?, username = ?, senha = ?, situacao = ?, data_cadastro = ? WHERE id_acesso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getEmailAcesso());
            stmt.setString(2, entity.getUsername());
            stmt.setString(3, entity.getSenha());
            stmt.setString(4, entity.getSituacao());
            stmt.setDate(5, new java.sql.Date(entity.getDataCadastro().getTime()));
            stmt.setLong(6, entity.getIdAcesso());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Você pode querer lançar uma exceção personalizada aqui
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_ACESSO WHERE id_acesso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Você pode querer lançar uma exceção personalizada aqui
        }
    }

    private Acesso mapResultSetToAcesso(ResultSet rs) throws SQLException {
        Acesso acesso = new Acesso();
        acesso.setIdAcesso(rs.getLong("id_acesso"));
        acesso.setEmailAcesso(rs.getString("email_acesso"));
        acesso.setUsername(rs.getString("username"));
        acesso.setSenha(rs.getString("senha"));
        acesso.setSituacao(rs.getString("situacao"));
        acesso.setDataCadastro(rs.getDate("data_cadastro"));
        return acesso;
    }
}