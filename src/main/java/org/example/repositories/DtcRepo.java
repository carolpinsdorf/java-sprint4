package org.example.repositories;

import org.example.entities.Dtc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DtcRepo extends _BaseRepoImpl<Dtc> {

    public DtcRepo(Connection connection) {
        super(connection);
    }

    @Override
    public Dtc findById(Long id) {
        Dtc dtc = null;
        try {
            String sql = "SELECT * FROM dtcs WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dtc = new Dtc(
                        rs.getLong("id"),
                        rs.getString("codigo"),
                        rs.getString("descricao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtc;
    }

    @Override
    public List<Dtc> findAll() {
        List<Dtc> dtcs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dtcs";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Dtc dtc = new Dtc(
                        rs.getLong("id"),
                        rs.getString("codigo"),
                        rs.getString("descricao")
                );
                dtcs.add(dtc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtcs;
    }

    @Override
    public void save(Dtc dtc) {
        Long novoId = null;
        try {
            String idQuery = "SELECT DTCS_SEQ.NEXTVAL FROM dual";
            try (PreparedStatement idStmt = connection.prepareStatement(idQuery);
                 ResultSet idRs = idStmt.executeQuery()) {
                if (idRs.next()) {
                    novoId = idRs.getLong(1); // Pega o próximo valor da sequência
                } else {
                    throw new SQLException("Não foi possível gerar um novo ID");
                }
            }

            String sql = "INSERT INTO dtcs (id, codigo, descricao) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, novoId); // Usar o ID gerado manualmente
            stmt.setString(2, dtc.getCodigo());
            stmt.setString(3, dtc.getDescricao());
            stmt.executeUpdate();

            dtc.setId(novoId); // Atribui o ID ao Dtc
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Dtc dtc) {
        try {
            String sql = "UPDATE dtcs SET codigo = ?, descricao = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, dtc.getCodigo());
            stmt.setString(2, dtc.getDescricao());
            stmt.setLong(3, dtc.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM dtcs WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
