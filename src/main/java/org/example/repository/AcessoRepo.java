package org.example.repository;

import org.example.entities.Acesso;
import org.example.exception.EntidadeNaoEncontradaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcessoRepo {

    private static final String SQL_SELECT_ALL = "SELECT * FROM T_ACESSO";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_ACESSO WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO T_ACESSO (email_acesso, username, senha, situacao, data_cadastro) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE T_ACESSO SET email_acesso = ?, username = ?, senha = ?, " +
            "situacao = ?, data_cadastro = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM T_ACESSO WHERE id = ?";

    private Connection connection;

    public AcessoRepo(Connection connection) {
        this.connection = connection;
    }
    public void cadastrar(Acesso acesso) throws SQLException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            if (acesso.getDataCadastro() == null) {
                acesso.setDataCadastro(new java.util.Date());
            }

            preencherStatement(acesso, stm);
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    acesso.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(Acesso acesso) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            preencherStatement(acesso, stm);
            stm.setInt(6, acesso.getId());
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Acesso não encontrado para atualização");
            }
        }
    }
    public List<Acesso> listar() throws SQLException {
        List<Acesso> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Acesso acesso = parseAcesso(resultSet);
                lista.add(acesso);
            }
        }
        return lista;
    }
    public Acesso pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return parseAcesso(resultSet);
                } else {
                    throw new EntidadeNaoEncontradaException("Acesso não encontrado");
                }
            }
        }
    }
    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Acesso não encontrado para remoção");
            }
        }
    }
    private static Acesso parseAcesso(ResultSet resultSet) throws SQLException {
        Acesso acesso = new Acesso();
        acesso.setId(resultSet.getInt("id"));
        acesso.setEmailAcesso(resultSet.getString("email_acesso"));
        acesso.setUsername(resultSet.getString("username"));
        acesso.setSenha(resultSet.getString("senha"));
        acesso.setSituacao(resultSet.getString("situacao"));
        acesso.setDataCadastro(resultSet.getDate("data_cadastro"));
        return acesso;
    }
    private static void preencherStatement(Acesso acesso, PreparedStatement stm) throws SQLException {
        stm.setString(1, acesso.getEmailAcesso());
        stm.setString(2, acesso.getUsername());
        stm.setString(3, acesso.getSenha());
        stm.setString(4, acesso.getSituacao());

        if (acesso.getDataCadastro() != null) {
            stm.setDate(5, new java.sql.Date(acesso.getDataCadastro().getTime()));
        } else {
            stm.setDate(5, new java.sql.Date(new java.util.Date().getTime())); // Define a data atual como fallback
        }
    }
}
