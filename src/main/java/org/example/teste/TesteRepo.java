package org.example.teste;

import org.example.entities.Cliente;
import org.example.repositories.ClienteRepo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class TesteRepo {
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL"; // Substitua pela sua URL de conexão
    private static final String USER = "RM555130";
    private static final String PASSWORD = "040506";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            ClienteRepo clienteRepo = new ClienteRepo(connection);

            Cliente novoCliente = new Cliente(null, "João Silva", "123456789", "12345678901");
            clienteRepo.save(novoCliente);
            System.out.println("Cliente criado: " + novoCliente);

            System.out.println("Todos os Clientes:");
            List<Cliente> clientes = clienteRepo.findAll();
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome() + ", Telefone: " + cliente.getTelefone() + ", CPF: " + cliente.getCpf());
            }

            if (!clientes.isEmpty()) {
                Cliente clienteParaAtualizar = clientes.get(0); // Pega o primeiro cliente
                clienteParaAtualizar.setNome("João da Silva");
                clienteRepo.update(clienteParaAtualizar);
                System.out.println("Cliente atualizado: " + clienteParaAtualizar);
            }

            if (!clientes.isEmpty()) {
                Cliente clienteParaExcluir = clientes.get(0); // Pega o primeiro cliente
                clienteRepo.delete(clienteParaExcluir.getId());
                System.out.println("Cliente excluído: " + clienteParaExcluir.getNome());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
