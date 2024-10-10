package org.example.services;

import org.example.entities.Cliente;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class ClienteValidator extends _BaseEntityValidatorImpl<Cliente> {

    private final Set<Long> cpfsExistentes = new HashSet<>();

    public boolean validaCpf(Long cpf) {
        if (cpf == null || String.valueOf(cpf).length() != 11) return false;
        return !cpfsExistentes.contains(cpf);
    }


    public boolean validaMaiorDeIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) return false;
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears() >= 18;
    }


    public void adicionarCpfExistente(Long cpf) {
        cpfsExistentes.add(cpf);
    }

    public boolean validarCliente(Cliente cliente) {
        return validaCpf(cliente.getCpfCliente()) &&
                validaMaiorDeIdade(cliente.getDataNascimento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
