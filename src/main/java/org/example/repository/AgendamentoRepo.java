package org.example.repository;

import org.example.entities.Agendamento;
import org.example.entities.Carro;
import org.example.entities.Oficina;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.services.AgendamentoValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepo {

    private static final String SQL_SELECT_ALL = "SELECT * FROM T_AGENDAMENTO";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_AGENDAMENTO WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO T_AGENDAMENTO (dthora_agendamento, status_agendamento, obs_agendamento, fk_oficina, fk_carro) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE T_AGENDAMENTO SET dthora_agendamento = ?, status_agendamento = ?, obs_agendamento = ?, fk_oficina = ?, fk_carro = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM T_AGENDAMENTO WHERE id = ?";

    private static final String SQL_SELECT_COM_OFICINA_CARRO = "SELECT " +
            "a.id, " +
            "a.dthora_agendamento, " +
            "a.status_agendamento, " +
            "a.obs_agendamento, " +
            "o.id AS oficina_id, " +
            "o.cnpj_oficina AS cnpj, " +
            "c.id AS carro_id, " +
            "c.ano_fabricacao AS ano_fabricacao " +
            "FROM T_AGENDAMENTO a " +
            "JOIN T_OFICINA o ON a.fk_oficina = o.id " +
            "JOIN T_CARRO c ON a.fk_carro = c.id";

    private Connection connection;
    private final AgendamentoValidator agendamentoValidator = new AgendamentoValidator();

    public AgendamentoRepo(Connection connection) {
        this.connection = connection;
    }

    public void cadastrar(Agendamento agendamento) throws SQLException {
        if (!agendamentoValidator.validaData(agendamento.getDthoraAgendamento().toString()) ||
                !agendamentoValidator.validaDescricaoServico(agendamento.getObsAgendamento()) ||
                !agendamentoValidator.validaNumeroCliente(String.valueOf(agendamento.getCarro().getId())) ||
                !agendamentoValidator.validaHora(agendamento.getDthoraAgendamento().toString().substring(11, 16))) {
            throw new IllegalArgumentException("Dados do agendamento inválidos");
        }

        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(agendamento, stm);
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    agendamento.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(Agendamento agendamento) throws SQLException, EntidadeNaoEncontradaException {
        if (!agendamentoValidator.validaData(agendamento.getDthoraAgendamento().toString()) ||
                !agendamentoValidator.validaDescricaoServico(agendamento.getObsAgendamento())) {
            throw new IllegalArgumentException("Dados do agendamento inválidos");
        }

        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            preencherStatement(agendamento, stm);
            stm.setInt(6, agendamento.getId());
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Agendamento não encontrado para atualização");
            }
        }
    }

    public List<Agendamento> listarComOficinaCarro() throws SQLException {
        List<Agendamento> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_COM_OFICINA_CARRO);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Agendamento agendamento = parseAgendamento(resultSet);
                lista.add(agendamento);
            }
        }
        return lista;
    }

    public Agendamento pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return parseAgendamento(resultSet);
                } else {
                    throw new EntidadeNaoEncontradaException("Agendamento não encontrado");
                }
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Agendamento não encontrado para remoção");
            }
        }
    }

    private static Agendamento parseAgendamento(ResultSet resultSet) throws SQLException {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(resultSet.getInt("id"));
        agendamento.setDthoraAgendamento(resultSet.getDate("dthora_agendamento"));
        agendamento.setStatusAgendamento(resultSet.getString("status_agendamento"));
        agendamento.setObsAgendamento(resultSet.getString("obs_agendamento"));

        // Configurando a oficina
        Oficina oficina = new Oficina();
        oficina.setId(resultSet.getInt("oficina_id"));
        oficina.setCnpjOficina(resultSet.getLong("cnpj"));  // Correspondente ao alias 'cnpj'
        agendamento.setOficina(oficina);

        // Configurando o carro
        Carro carro = new Carro();
        carro.setId(resultSet.getInt("carro_id"));
        carro.setAnoFabricacao(resultSet.getInt("ano_fabricacao"));
        agendamento.setCarro(carro);

        return agendamento;
    }


    private static void preencherStatement(Agendamento agendamento, PreparedStatement stm) throws SQLException {
        stm.setDate(1, new java.sql.Date(agendamento.getDthoraAgendamento().getTime()));
        stm.setString(2, agendamento.getStatusAgendamento());
        stm.setString(3, agendamento.getObsAgendamento());
        stm.setInt(4, agendamento.getOficina().getId());
        stm.setInt(5, agendamento.getCarro().getId());
    }
}
