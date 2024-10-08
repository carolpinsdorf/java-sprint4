package org.example.repositories;

import org.example.entities.Carro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroRepo extends _BaseRepoImpl<Carro> {

    public CarroRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Carro findById(Long id) {
        String sql = "SELECT * FROM T_CARRO WHERE id_carro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCarro(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM T_CARRO";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Carro carro = mapResultSetToCarro(rs);
                carros.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }

    @Override
    public void save(Carro entity) {
        String sql = "INSERT INTO T_CARRO (placa, modelo, marca, ano_fabricacao, torque, cambio, combustivel, cor, quilometragem, fk_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getPlaca());
            stmt.setString(2, entity.getModelo());
            stmt.setString(3, entity.getMarca());
            stmt.setInt(4, entity.getAnoFabricacao());
            stmt.setObject(5, entity.getTorque(), Types.INTEGER);
            stmt.setString(6, entity.getCambio());
            stmt.setString(7, entity.getCombustivel());
            stmt.setString(8, entity.getCor());
            stmt.setObject(9, entity.getQuilometragem(), Types.INTEGER);
            stmt.setLong(10, entity.getFkCliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Carro entity) {
        String sql = "UPDATE T_CARRO SET placa = ?, modelo = ?, marca = ?, ano_fabricacao = ?, torque = ?, cambio = ?, combustivel = ?, cor = ?, quilometragem = ?, fk_cliente = ? WHERE id_carro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getPlaca());
            stmt.setString(2, entity.getModelo());
            stmt.setString(3, entity.getMarca());
            stmt.setInt(4, entity.getAnoFabricacao());
            stmt.setObject(5, entity.getTorque(), Types.INTEGER);
            stmt.setString(6, entity.getCambio());
            stmt.setString(7, entity.getCombustivel());
            stmt.setString(8, entity.getCor());
            stmt.setObject(9, entity.getQuilometragem(), Types.INTEGER);
            stmt.setLong(10, entity.getFkCliente());
            stmt.setLong(11, entity.getIdCarro());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM T_CARRO WHERE id_carro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Carro mapResultSetToCarro(ResultSet rs) throws SQLException {
        return new Carro(
                rs.getLong("id_carro"),
                rs.getString("placa"),
                rs.getString("modelo"),
                rs.getString("marca"),
                rs.getInt("ano_fabricacao"),
                rs.getObject("torque", Integer.class),
                rs.getString("cambio"),
                rs.getString("combustivel"),
                rs.getString("cor"),
                rs.getObject("quilometragem", Integer.class),
                rs.getLong("fk_cliente")
        );
    }
}
