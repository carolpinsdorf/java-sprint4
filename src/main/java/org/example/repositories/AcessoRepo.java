package org.example.repositories;

import org.example.entities.Acesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcessoRepo extends _BaseRepoImpl<Acesso> {

    public AcessoRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Acesso findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Acesso acesso = null;
        String sql = "SELECT * FROM Acesso WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                acesso = new Acesso(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return acesso;
    }


    @Override
    public List<Acesso> findAll() {
        List<Acesso> acessos = new ArrayList<>();
        String sql = "SELECT * FROM Acesso";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Acesso acesso = new Acesso(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                acessos.add(acesso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return acessos;
    }
    public void save(Acesso acesso) {
        try {
            String sql = "INSERT INTO Acesso (id, username, password) " +
                    "VALUES (ACESSO_SEQ.NEXTVAL, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, acesso.getUsername());
            stmt.setString(2, acesso.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    @Override
    public void update(Acesso acesso) {
        String sql = "UPDATE Acesso SET username = ?, password = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, acesso.getUsername());
            stmt.setString(2, acesso.getPassword());
            stmt.setLong(3, acesso.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Acesso WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
