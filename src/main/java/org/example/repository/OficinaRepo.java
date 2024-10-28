package org.example.repository;

import org.example.entities.Oficina;
import org.example.entities.Acesso;
import org.example.exception.EntidadeNaoEncontradaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OficinaRepo {

    private static final String SQL_SELECT_ALL = "SELECT * FROM T_OFICINA";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM T_OFICINA WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO T_OFICINA (cnpj_oficina, fk_acesso) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE T_OFICINA SET cnpj_oficina = ?, fk_acesso = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM T_OFICINA WHERE id = ?";

    private Connection connection;

    public OficinaRepo(Connection connection) {
        this.connection = connection;
    }

    public void cadastrar(Oficina oficina) throws SQLException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stm.setLong(1, oficina.getCnpjOficina());
            stm.setInt(2, oficina.getAcesso().getId());
            stm.executeUpdate();

            try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    oficina.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void atualizar(Oficina oficina) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_UPDATE)) {
            stm.setLong(1, oficina.getCnpjOficina());
            stm.setInt(2, oficina.getAcesso().getId());
            stm.setInt(3, oficina.getId());

            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Oficina não encontrada para atualização");
            }
        }
    }

    public List<Oficina> listar() throws SQLException {
        List<Oficina> lista = new ArrayList<>();
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stm.executeQuery()) {
            while (resultSet.next()) {
                Oficina oficina = parseOficina(resultSet);
                lista.add(oficina);
            }
        }
        return lista;
    }

    public Oficina pesquisarPorId(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            stm.setInt(1, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return parseOficina(resultSet);
                } else {
                    throw new EntidadeNaoEncontradaException("Oficina não encontrada");
                }
            }
        }
    }

    public void remover(int id) throws SQLException, EntidadeNaoEncontradaException {
        try (PreparedStatement stm = connection.prepareStatement(SQL_DELETE)) {
            stm.setInt(1, id);
            if (stm.executeUpdate() == 0) {
                throw new EntidadeNaoEncontradaException("Oficina não encontrada para remoção");
            }
        }
    }

    private static Oficina parseOficina(ResultSet resultSet) throws SQLException {
        Oficina oficina = new Oficina();
        oficina.setId(resultSet.getInt("id"));
        oficina.setCnpjOficina(resultSet.getLong("cnpj_oficina"));

        Acesso acesso = new Acesso();
        acesso.setId(resultSet.getInt("fk_acesso"));
        oficina.setAcesso(acesso);

        return oficina;
    }
}