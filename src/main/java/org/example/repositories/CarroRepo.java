package org.example.repositories;

import org.example.entities.Carro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroRepo implements _EntidadeRepo<Carro> {
    private Connection connection;

    public CarroRepo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Carro findById(int id) {
        Carro carro = null;
        String sql = "SELECT * FROM T_CARRO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                carro = new Carro(
                        rs.getInt("id"),
                        rs.getDate("data_criacao"), // Assumindo que você tem esses campos na tabela
                        rs.getDate("data_atualizacao"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("ano_fabricacao"),
                        null // Substitua isso por um objeto Cliente, se necessário
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carro;
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM T_CARRO";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Carro carro = new Carro(
                        rs.getInt("id"),
                        rs.getDate("data_criacao"),
                        rs.getDate("data_atualizacao"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("ano_fabricacao"),
                        null // Substitua isso por um objeto Cliente, se necessário
                );
                carros.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }

    @Override
    public void save(Carro entity) {
        String sql = "INSERT INTO T_CARRO (placa, modelo, marca, ano_fabricacao, fk_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getPlaca());
            stmt.setString(2, entity.getModelo());
            stmt.setString(3, entity.getMarca());
            stmt.setInt(4, entity.getAnoFabricacao());

            // Use o ID do cliente se ele não for nulo
            if (entity.getCliente() != null) {
                stmt.setInt(5, entity.getCliente().getId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Carro entity) {
        String sql = "UPDATE T_CARRO SET placa = ?, modelo = ?, marca = ?, ano_fabricacao = ?, fk_cliente = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getPlaca());
            stmt.setString(2, entity.getModelo());
            stmt.setString(3, entity.getMarca());
            stmt.setInt(4, entity.getAnoFabricacao());

            // Use o ID do cliente se ele não for nulo
            if (entity.getCliente() != null) {
                stmt.setInt(5, entity.getCliente().getId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setInt(6, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_CARRO WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
