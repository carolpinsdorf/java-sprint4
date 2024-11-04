package org.example.repositories;

import org.example.entities.Acesso;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.infrastructure.Log4jLogger;
import org.example.services.AcessoValidator;

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
    private final Log4jLogger logger;
    AcessoValidator acessoValidator = new AcessoValidator();

    public AcessoRepo(Connection connection) {
        this.connection = connection;
        this.logger = new Log4jLogger(AcessoRepo.class);
    }

    public void cadastrar(Acesso acesso) throws SQLException {
        logger.info("Iniciando validação do acesso");
        if (!acessoValidator.validarAcesso(acesso)) {
            logger.warn("Falha na validação do acesso");
            throw new IllegalArgumentException("Dados de acesso inválidos");
        }

        if (acesso.getSituacao() == null) {
            acesso.setSituacao("ativo");
        }

        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            if (acesso.getDataCadastro() == null) {
                acesso.setDataCadastro(new java.util.Date());
            }

            preencherStatement(acesso, stm);
            stm.executeUpdate();
            logger.info("Acesso cadastrado com sucesso");
        } catch (SQLException e) {
            logger.error("Erro ao cadastrar acesso", e);
            throw e;
        }
    }


    public void atualizar(Acesso acesso) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Iniciando atualização do acesso ID: " + acesso.getId());
        if (!acessoValidator.validarAcesso(acesso)) {
            logger.warn("Falha na validação dos dados de acesso");
            throw new IllegalArgumentException("Dados de acesso inválidos");
        }

        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            preencherStatement(acesso, stm);
            stm.setInt(6, acesso.getId());
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Acesso não encontrado para atualização");
            }

            acessoValidator.adicionarAcessoExistente(acesso.getEmailAcesso(), acesso.getUsername());
            logger.info("Acesso atualizado com sucesso");
        } catch (SQLException e) {
            logger.error("Erro ao atualizar acesso", e);
            throw e;
        }
    }

    public List<Acesso> listar() throws SQLException {
        logger.info("Listando todos os acessos");
        List<Acesso> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Acesso acesso = parseAcesso(resultSet);
                lista.add(acesso);
            }
        } catch (SQLException e) {
            logger.error("Erro ao listar acessos", e);
            throw e;
        }
        return lista;
    }

    public Acesso pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Pesquisando acesso por ID: " + id);
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("Acesso encontrado");
                    return parseAcesso(resultSet);
                } else {
                    logger.warn("Acesso não encontrado para o ID: " + id);
                    throw new EntidadeNaoEncontradaException("Acesso não encontrado");
                }
            }
        } catch (SQLException e) {
            logger.error("Erro ao pesquisar acesso por ID", e);
            throw e;
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Tentando remover acesso com ID: " + id);
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            int linhasAfetadas = stm.executeUpdate();
            logger.info("Linhas afetadas: " + linhasAfetadas);
            if (linhasAfetadas == 0) {
                logger.warn("Acesso não encontrado para remoção com ID: " + id);
                throw new EntidadeNaoEncontradaException("Acesso não encontrado para remoção");
            }
            logger.info("Acesso removido com sucesso");
        } catch (SQLException e) {
            logger.error("Erro ao tentar remover acesso", e);
            throw e;
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
            stm.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
        }
    }
}