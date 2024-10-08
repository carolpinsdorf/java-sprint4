package org.example.repositories;

import org.example.entities.Carro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroRepo extends _BaseRepoImpl<Carro> {

    public CarroRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Carro findById(Long id) {
        String query = "SELECT * FROM carros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Carro(
                        rs.getLong("id"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getInt("ano"),
                        rs.getInt("quilometragem"),
                        rs.getString("marca")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carros = new ArrayList<>();
        String query = "SELECT * FROM carros";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                carros.add(new Carro(
                        rs.getLong("id"),
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getInt("ano"),
                        rs.getInt("quilometragem"),
                        rs.getString("marca")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }

    @Override
    public void save(Carro carro) {
        String query = "INSERT INTO carros (modelo, placa, ano, quilometragem, marca) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getPlaca());
            stmt.setInt(3, carro.getAno());
            stmt.setInt(4, carro.getQuilometragem());
            stmt.setString(5, carro.getMarca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Carro carro) {
        String query = "UPDATE carros SET modelo = ?, placa = ?, ano = ?, quilometragem = ?, marca = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getPlaca());
            stmt.setInt(3, carro.getAno());
            stmt.setInt(4, carro.getQuilometragem());
            stmt.setString(5, carro.getMarca());
            stmt.setLong(6, carro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM carros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
