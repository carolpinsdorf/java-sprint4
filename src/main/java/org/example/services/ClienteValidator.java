package org.example.services;

import org.example.entities.Cliente;

import java.util.regex.Pattern;

public class ClienteValidator extends _BaseEntityValidatorImpl<Cliente> {

    public boolean validaNome(String nome) {
        return nome != null && nome.trim().length() >= 3;
    }

    public boolean validaCPF(String cpf) {
        String regexCPF = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        return Pattern.matches(regexCPF, cpf);
    }

    public boolean validaEmail(String email) {
        String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";  // Regex para e-mail básico
        return Pattern.matches(regexEmail, email);
    }

    public boolean validaTelefone(String telefone) {
        String regexTelefone = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$";
        return Pattern.matches(regexTelefone, telefone);
    }

    public boolean validaEndereco(String endereco) {
        return validaCampoObg(endereco);
    }
}
