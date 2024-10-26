package org.example.services;

import org.example.entities.Oficina;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class OficinaValidator extends _BaseEntityValidatorImpl<Oficina> {

    private final Set<Long> cnpjsExistentes = new HashSet<>();

    // Regex para validar CNPJ
    private static final String CNPJ_REGEX = "^(\\d{14})$"; // Aceita apenas números e verifica se tem 14 dígitos

    public boolean validaCnpj(long cnpj) {
        String cnpjStr = String.valueOf(cnpj);
        // Verifica o comprimento e o padrão
        if (cnpjStr.length() != 14 || !Pattern.matches(CNPJ_REGEX, cnpjStr)) return false;
        return !cnpjsExistentes.contains(cnpj);
    }

    public void adicionarCnpjExistente(long cnpj) {
        cnpjsExistentes.add(cnpj);
    }

    public boolean validarOficina(Oficina oficina) {
        return validaCnpj(oficina.getCnpjOficina());
    }
}

