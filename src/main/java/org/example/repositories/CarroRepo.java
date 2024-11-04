package org.example.repositories;

import org.example.entities.Carro;
import org.example.entities.Cliente;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.services.CarroValidator;
import org.example.infrastructure.Log4jLogger;

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
    private final Log4jLogger logger = new Log4jLogger(CarroRepo.class);

    public CarroRepo(Connection connection) {
        this.connection = connection;
        this.validador = new CarroValidator();
    }

    public void cadastrar(Carro carro) throws SQLException {
        validarDadosCarro(carro);
        logger.info("Iniciando o cadastro do carro: " + carro);

        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(carro, stm);
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    carro.setId(generatedKeys.getInt(1));
                    logger.info("Carro cadastrado com sucesso, ID gerado: " + carro.getId());
                }
            }
        }
        validador.adicionarPlacaExistente(carro.getPlaca());
    }

    public void atualizar(Carro carro) throws SQLException, EntidadeNaoEncontradaException {
        validarDadosCarro(carro);

        logger.info("Iniciando a atualização do carro com ID: " + carro.getId());
        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            preencherStatement(carro, stm);
            stm.setInt(11, carro.getId());
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Carro não encontrado para atualização");
            }
            logger.info("Carro atualizado com sucesso, ID: " + carro.getId());
        }
    }

    public List<Carro> listar() throws SQLException {
        logger.info("Listando todos os carros...");
        List<Carro> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Carro carro = parseCarro(resultSet);
                lista.add(carro);
            }
            logger.info("Listagem de carros concluída com sucesso. Total de carros: " + lista.size());
        }
        return lista;
    }

    public Carro pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Pesquisando carro por ID: " + id);
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("Carro encontrado com ID: " + id);
                    return parseCarro(resultSet);
                } else {
                    throw new EntidadeNaoEncontradaException("Carro não encontrado");
                }
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        logger.info("Removendo carro com ID: " + id);
        pesquisarPorId(id);

        System.out.println("Carro encontrado. Preparando para remover.");
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            int affectedRows = stm.executeUpdate();
            System.out.println("Linhas afetadas pela remoção: " + affectedRows);
            if (affectedRows == 0) {
                throw new EntidadeNaoEncontradaException("Carro não encontrado para remoção");
            }
            logger.info("Carro removido com sucesso, ID: " + id);
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

        if (carro.getCambio() != null) {
            stm.setString(6, carro.getCambio());
        } else {
            stm.setNull(6, Types.VARCHAR);
        }

        if (carro.getCombustivel() != null) {
            stm.setString(7, carro.getCombustivel());
        } else {
            stm.setNull(7, Types.VARCHAR);
        }

        if (carro.getCor() != null) {
            stm.setString(8, carro.getCor());
        } else {
            stm.setNull(8, Types.VARCHAR);
        }

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
                !validador.validaPlaca(carro.getPlaca())) {
            logger.warn("Dados inválidos para o carro: " + carro);
            throw new IllegalArgumentException("Dados inválidos para o carro.");
        }
    }
}
