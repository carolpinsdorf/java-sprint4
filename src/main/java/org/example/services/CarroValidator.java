package org.example.services;

import org.example.entitities.Carro;

import java.util.List;
import java.util.regex.Pattern;

public class CarroValidator {

    private static final String regexPlaca = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$";

    public boolean validar(Carro carro) {
        if (!validarPlaca(carro.getPlaca())) {
            System.out.println("Placa inválida. Deve seguir o formato ABC1D23.");
            return false;
        }

        if (carro.getMarca() == null || carro.getMarca().trim().isEmpty()) {
            System.out.println("Marca não pode ser vazia.");
            return false;
        }

        if (carro.getModelo() == null || carro.getModelo().trim().isEmpty()) {
            System.out.println("Modelo não pode ser vazio.");
            return false;
        }

        if (carro.getAno() < 1886 || carro.getAno() > 2024) {
            System.out.println("Ano inválido. Deve estar entre 1886 e o ano atual.");
            return false;
        }

        if (carro.getQuilometragem() < 0) {
            System.out.println("Quilometragem inválida. Não pode ser negativa.");
            return false;
        }

        return true;
    }

    public boolean validarPlaca(String placa) {
        return Pattern.matches(regexPlaca, placa);
    }

    public boolean validarLista(List<Carro> carros) {
        if (carros.isEmpty()) {
            System.out.println("Nenhum carro encontrado.");
            return false;
        }
        return true;
    }

    public boolean confirmarOperacao(String confirmacao) {
        return confirmacao.equalsIgnoreCase("S");
    }
}
