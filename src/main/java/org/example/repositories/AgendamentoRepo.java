package org.example.repositories;

import org.example.entities.Agendamento;
import org.example.entities.Carro;
import org.example.entities.Oficina;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.infrastructure.Log4jLogger;
import org.example.services.AgendamentoValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepo {

    private static final String SQL_SELECT_ALL = "SELECT * FROM T_AGENDAMENTO";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_AGENDAMENTO WHERE id = ?";
    private static final String SQL_INSERT =
            "INSERT INTO T_AGENDAMENTO (dthora_agendamento, status_agendamento, obs_agendamento, fk_oficina, fk_carro) " +
                    "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE T_AGENDAMENTO SET dthora_agendamento = ?, status_agendamento = ?, obs_agendamento = ?, fk_oficina = ?, fk_carro = ? " +
                    "WHERE id = ?";
    private static final String SQL_DELETE =
            "DELETE FROM T_AGENDAMENTO WHERE id = ?";

    private Connection connection;
    private final AgendamentoValidator agendamentoValidator = new AgendamentoValidator();
    private final Log4jLogger logger = new Log4jLogger(AgendamentoRepo.class);

    public AgendamentoRepo(Connection connection) {
        this.connection = connection;
    }

    public void cadastrar(Agendamento agendamento) throws SQLException {
        logger.info("Iniciando cadastro de agendamento");
        if (!agendamentoValidator.validaData(agendamento.getDthoraAgendamento().toString()) ||
                !agendamentoValidator.validaDescricaoServico(agendamento.getObsAgendamento())) {
            logger.warn("Falha na validação dos dados do agendamento.");
            throw new IllegalArgumentException("Dados do agendamento inválidos");
        }

        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(agendamento, stm);
            stm.executeUpdate();
            logger.info("Agendamento cadastrado com sucesso.");

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    agendamento.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(Agendamento agendamento) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Iniciando atualização de agendamento com ID: " + agendamento.getId());
        if (!agendamentoValidator.validaData(agendamento.getDthoraAgendamento().toString()) ||
                !agendamentoValidator.validaDescricaoServico(agendamento.getObsAgendamento())) {
            logger.warn("Falha na validação dos dados do agendamento.");
            throw new IllegalArgumentException("Dados do agendamento inválidos");
        }

        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            preencherStatement(agendamento, stm);
            stm.setInt(6, agendamento.getId());
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Agendamento não encontrado para atualização");
            }
            logger.info("Agendamento atualizado com sucesso.");
        }
    }

    public List<Agendamento> listar() throws SQLException {
        logger.info("Listando todos os agendamentos.");
        List<Agendamento> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                lista.add(parseAgendamento(resultSet));
            }
            logger.debug("Total de agendamentos encontrados: " + lista.size());
        }
        return lista;
    }

    public Agendamento pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Pesquisando agendamento por ID: " + id);
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("Agendamento encontrado.");
                    return parseAgendamento(resultSet);
                } else {
                    throw new EntidadeNaoEncontradaException("Agendamento não encontrado");
                }
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Tentando remover agendamento com ID: " + id);
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            int rowsAffected = stm.executeUpdate();

            if (rowsAffected == 0) {
                logger.warn("Agendamento não encontrado para remoção.");
                throw new EntidadeNaoEncontradaException("Agendamento não encontrado para remoção");
            }
            else {
                System.out.println("Agendamento com ID " + id + " removido com sucesso.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao tentar remover o agendamento: " + e.getMessage());
            throw e;
        }
    }


    private static Agendamento parseAgendamento(ResultSet resultSet) throws SQLException {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(resultSet.getInt("id"));
        agendamento.setDthoraAgendamento(resultSet.getTimestamp("dthora_agendamento").toLocalDateTime());
        agendamento.setStatusAgendamento(resultSet.getString("status_agendamento"));
        agendamento.setObsAgendamento(resultSet.getString("obs_agendamento"));

        Oficina oficina = new Oficina();
        oficina.setId(resultSet.getInt("fk_oficina"));
        agendamento.setOficina(oficina);

        Carro carro = new Carro();
        carro.setId(resultSet.getInt("fk_carro"));
        agendamento.setCarro(carro);

        return agendamento;
    }

    private static void preencherStatement(Agendamento agendamento, PreparedStatement stm) throws SQLException {
        stm.setTimestamp(1, Timestamp.valueOf(agendamento.getDthoraAgendamento()));
        stm.setString(2, agendamento.getStatusAgendamento());
        stm.setString(3, agendamento.getObsAgendamento());
        stm.setInt(4, agendamento.getOficina().getId());
        stm.setInt(5, agendamento.getCarro().getId());
    }
}
