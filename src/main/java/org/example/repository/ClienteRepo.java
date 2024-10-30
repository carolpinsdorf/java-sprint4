package org.example.repository;

import org.example.entities.Acesso;
import org.example.entities.Cliente;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.services.ClienteValidator;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepo {

    private static final String SQL_SELECT_ALL = "SELECT * FROM T_CLIENTE";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_CLIENTE WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO T_CLIENTE (cpf_cliente, nm_cliente, rg_cliente, dt_nascimento, sx_cliente, estado_civil, fk_acesso) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE T_CLIENTE SET cpf_cliente = ?, nm_cliente = ?, rg_cliente = ?, " +
            "dt_nascimento = ?, sx_cliente = ?, estado_civil = ?, fk_acesso = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM T_CLIENTE WHERE id = ?";

    private final Connection connection;
    private final ClienteValidator validador;

    public ClienteRepo(Connection connection) {
        this.connection = connection;
        this.validador = new ClienteValidator();
    }

    public void cadastrar(Cliente cliente) throws SQLException {
        validarDadosCliente(cliente);

        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(cliente, stm);
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1));
                }
            }
        }
        validador.adicionarCpfExistente(cliente.getCpfCliente());
    }

    public void atualizar(Cliente cliente) throws SQLException, EntidadeNaoEncontradaException {
        validarDadosCliente(cliente);

        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            preencherStatement(cliente, stm);
            stm.setInt(8, cliente.getId());
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Cliente não encontrado para atualização");
            }
        }
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Cliente cliente = parseCliente(resultSet);
                lista.add(cliente);
            }
        }
        return lista;
    }

    public Cliente pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return parseCliente(resultSet);
                } else {
                    throw new EntidadeNaoEncontradaException("Cliente não encontrado");
                }
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Cliente não encontrado para remoção");
            }
        }
    }

    private static Cliente parseCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("id"));
        cliente.setCpfCliente(resultSet.getLong("cpf_cliente"));
        cliente.setNomeCliente(resultSet.getString("nm_cliente"));
        cliente.setRgCliente(resultSet.getString("rg_cliente"));

        // Obter LocalDate diretamente
        cliente.setDataNascimento(resultSet.getObject("dt_nascimento", LocalDate.class));

        cliente.setSexoCliente(resultSet.getString("sx_cliente"));
        cliente.setEstadoCivil(resultSet.getString("estado_civil"));

        Acesso acesso = new Acesso();
        acesso.setId(resultSet.getInt("fk_acesso"));
        cliente.setAcesso(acesso);

        return cliente;
    }


    private static void preencherStatement(Cliente cliente, PreparedStatement stm) throws SQLException {
        stm.setLong(1, cliente.getCpfCliente());
        stm.setString(2, cliente.getNomeCliente());
        stm.setString(3, cliente.getRgCliente());

        // Converte LocalDate para java.sql.Date
        if (cliente.getDataNascimento() != null) {
            stm.setDate(4, java.sql.Date.valueOf(cliente.getDataNascimento()));
        } else {
            stm.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
        }

        stm.setString(5, cliente.getSexoCliente());
        stm.setString(6, cliente.getEstadoCivil());
        stm.setInt(7, cliente.getAcesso().getId());
    }


    private void validarDadosCliente(Cliente cliente) {
        if (!validador.validaCampoObg(cliente.getNomeCliente()) ||
                !validador.validaCampoObg(cliente.getRgCliente()) ||
                !validador.validaCpf(cliente.getCpfCliente()) ||
                !validador.validaMaiorDeIdade(cliente.getDataNascimento())) {
            throw new IllegalArgumentException("Dados inválidos para o cliente.");
        }
    }

}
