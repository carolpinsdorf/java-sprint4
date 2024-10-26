package org.example.repositories;

import org.example.entities.*;
import org.example.infrastructure.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepo implements _EntidadeRepo<Agendamento> {

    @Override
    public Agendamento findById(int id) {
        Agendamento agendamento = null;
        String sql = "SELECT * FROM T_AGENDAMENTO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                agendamento = new Agendamento(
                        rs.getInt("id"),
                        rs.getTimestamp("dthora_agendamento"),
                        rs.getString("status_agendamento"),
                        findOficinaById(rs.getInt("fk_oficina")), // Busca a oficina corretamente
                        findCarroById(rs.getInt("fk_carro")) // Busca o carro corretamente
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamento;
    }

    @Override
    public List<Agendamento> findAll() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM T_AGENDAMENTO";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                        rs.getInt("id"),
                        rs.getTimestamp("dthora_agendamento"),
                        rs.getString("status_agendamento"),
                        findOficinaById(rs.getInt("fk_oficina")),
                        findCarroById(rs.getInt("fk_carro"))
                );
                agendamentos.add(agendamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agendamentos;
    }

    @Override
    public void save(Agendamento entity) {
        String sql = "INSERT INTO T_AGENDAMENTO (dthora_agendamento, status_agendamento, fk_oficina, fk_carro) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(entity.getDthoraAgendamento().getTime()));
            stmt.setString(2, entity.getStatusAgendamento());
            stmt.setInt(3, entity.getOficina().getId());
            stmt.setInt(4, entity.getCarro().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Agendamento entity) {
        String sql = "UPDATE T_AGENDAMENTO SET dthora_agendamento = ?, status_agendamento = ?, fk_oficina = ?, fk_carro = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(entity.getDthoraAgendamento().getTime()));
            stmt.setString(2, entity.getStatusAgendamento());
            stmt.setInt(3, entity.getOficina().getId());
            stmt.setInt(4, entity.getCarro().getId());
            stmt.setInt(5, entity.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM T_AGENDAMENTO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos auxiliares para buscar Oficina e Carro
    private Oficina findOficinaById(int id) {
        Oficina oficina = null;
        String sql = "SELECT * FROM T_OFICINA WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Acesso acesso = findAcessoById(rs.getInt("fk_acesso"));
                oficina = new Oficina(
                        rs.getInt("id"),
                        rs.getInt("cnpj_oficina"), // CNPJ da oficina
                        acesso // Relacionamento com Acesso
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oficina;
    }

    private Carro findCarroById(int id) {
        Carro carro = null;
        String sql = "SELECT * FROM T_CARRO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = findClienteById(rs.getInt("fk_cliente"));
                carro = new Carro(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("ano_fabricacao"),
                        cliente // Relacionamento com Cliente
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carro;
    }

    // Método para buscar Acesso relacionado à Oficina
    private Acesso findAcessoById(int id) {
        Acesso acesso = null;
        String sql = "SELECT * FROM T_ACESSO WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                acesso = new Acesso(
                        rs.getInt("id"),
                        rs.getString("email_acesso"),
                        rs.getString("username"),
                        rs.getString("senha")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acesso;
    }

    // Método para buscar Cliente relacionado ao Carro
    private Cliente findClienteById(int id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM T_CLIENTE WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getLong("cpf_cliente"), // Supondo que você esteja obtendo um Long do ResultSet
                        rs.getString("nome_cliente"),
                        rs.getDate("data_nascimento"), // Ajuste conforme a necessidade
                        null // ou um objeto Acesso, se necessário
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
