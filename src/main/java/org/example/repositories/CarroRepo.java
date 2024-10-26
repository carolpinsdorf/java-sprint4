package org.example.repositories;

import org.example.entities.Carro;
import org.example.entities.Cliente;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroRepo implements _EntidadeRepo<Carro> {

    @Override
    public Carro findById(int id) {
        Carro carro = null;
        String sql = "SELECT * FROM T_CARRO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                carro = mapResultSetToCarro(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar erro de forma mais adequada em produção
        }
        return carro;
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carros = new ArrayList<>();
        String sql = "SELECT * FROM T_CARRO";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Carro carro = mapResultSetToCarro(rs);
                carros.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar erro de forma mais adequada em produção
        }
        return carros;
    }

    @Override
    public void save(Carro carro) {
        String sql = "INSERT INTO T_CARRO (placa, modelo, marca, ano_fabricacao, fk_cliente) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getMarca());
            stmt.setInt(4, carro.getAnoFabricacao());
            stmt.setInt(5, carro.getCliente().getId()); // Presumindo que o Cliente tenha um método getId()
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar erro de forma mais adequada em produção
        }
    }

    @Override
    public void update(Carro carro) {
        String sql = "UPDATE T_CARRO SET placa = ?, modelo = ?, marca = ?, ano_fabricacao = ?, fk_cliente = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getMarca());
            stmt.setInt(4, carro.getAnoFabricacao());
            stmt.setInt(5, carro.getCliente().getId()); // Presumindo que o Cliente tenha um método getId()
            stmt.setInt(6, carro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar erro de forma mais adequada em produção
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_CARRO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Tratar erro de forma mais adequada em produção
        }
    }

    private Carro mapResultSetToCarro(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String placa = rs.getString("placa");
        String modelo = rs.getString("modelo");
        String marca = rs.getString("marca");
        int anoFabricacao = rs.getInt("ano_fabricacao");

        Cliente cliente = new Cliente();


        return new Carro(id, placa, modelo, marca, anoFabricacao, cliente);
    }
}
