package org.example.repository;

import org.example.entities.Carro;
import org.example.entities.Cliente;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.services.CarroValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroRepo {

    private static final String SQL_SELECT_ALL = "SELECT * FROM T_CARRO";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_CARRO WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO T_CARRO (placa, modelo, marca, ano_fabricacao, torque, cambio, combustivel, cor, quilometragem, fk_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE T_CARRO SET placa = ?, modelo = ?, marca = ?, ano_fabricacao = ?, torque = ?, cambio = ?, combustivel = ?, cor = ?, quilometragem = ?, fk_cliente = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM T_CARRO WHERE id = ?";

    private Connection connection;
    private final CarroValidator validador;

    public CarroRepo(Connection connection) {
        this.connection = connection;
        this.validador = new CarroValidator();
    }

    public void cadastrar(Carro carro) throws SQLException {
        validarDadosCarro(carro);

        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(carro, stm);
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    carro.setId(generatedKeys.getInt(1));
                }
            }
        }
        validador.adicionarPlacaExistente(carro.getPlaca());
    }

    public void atualizar(Carro carro) throws SQLException, EntidadeNaoEncontradaException {
        validarDadosCarro(carro);

        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            preencherStatement(carro, stm);
            stm.setInt(11, carro.getId());
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Carro não encontrado para atualização");
            }
        }
    }

    public List<Carro> listar() throws SQLException {
        List<Carro> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Carro carro = parseCarro(resultSet);
                lista.add(carro);
            }
        }
        return lista;
    }

    public Carro pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return parseCarro(resultSet);
                } else {
                    throw new EntidadeNaoEncontradaException("Carro não encontrado");
                }
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Carro não encontrado para remoção");
            }
        }
    }

    private static Carro parseCarro(ResultSet resultSet) throws SQLException {
        Carro carro = new Carro();
        carro.setId(resultSet.getInt("id"));
        carro.setPlaca(resultSet.getString("placa"));
        carro.setModelo(resultSet.getString("modelo"));
        carro.setMarca(resultSet.getString("marca"));
        carro.setAnoFabricacao(resultSet.getInt("ano_fabricacao"));
        carro.setTorque(resultSet.getObject("torque", Integer.class));
        carro.setCambio(resultSet.getString("cambio"));
        carro.setCombustivel(resultSet.getString("combustivel"));
        carro.setCor(resultSet.getString("cor"));
        carro.setQuilometragem(resultSet.getObject("quilometragem", Integer.class));

        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("fk_cliente"));
        carro.setCliente(cliente);

        return carro;
    }

    private static void preencherStatement(Carro carro, PreparedStatement stm) throws SQLException {
        stm.setString(1, carro.getPlaca());
        stm.setString(2, carro.getModelo());
        stm.setString(3, carro.getMarca());
        stm.setInt(4, carro.getAnoFabricacao());

        if (carro.getTorque() != null) {
            stm.setInt(5, carro.getTorque());
        } else {
            stm.setNull(5, Types.INTEGER);
        }

        stm.setString(6, carro.getCambio());
        stm.setString(7, carro.getCombustivel());
        stm.setString(8, carro.getCor());

        if (carro.getQuilometragem() != null) {
            stm.setInt(9, carro.getQuilometragem());
        } else {
            stm.setNull(9, Types.INTEGER);
        }

        if (carro.getCliente() != null) {
            stm.setInt(10, carro.getCliente().getId());
        } else {
            stm.setNull(10, Types.INTEGER);
        }
    }

    private void validarDadosCarro(Carro carro) {
        if (!validador.validaCampoObg(carro.getModelo()) ||
                !validador.validaCampoObg(carro.getMarca()) ||
                !validador.validaAno(carro.getAnoFabricacao()) ||
                !validador.validaNumPositivo(carro.getTorque() != null ? carro.getTorque() : 0) ||
                !validador.validaPlaca(carro.getPlaca())) {
            throw new IllegalArgumentException("Dados inválidos para o carro.");
        }
    }
}
