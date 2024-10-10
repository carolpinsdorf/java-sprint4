package org.example.services;

import org.example.entities.Oficina;

import java.util.HashSet;
import java.util.Set;

public class OficinaValidator extends _BaseEntityValidatorImpl<Oficina> {

    private final Set<Integer> cnpjsExistentes = new HashSet<>();

    public boolean validaCnpj(int cnpj) {
        String cnpjStr = String.valueOf(cnpj);
        if (cnpjStr.length() != 14) return false;
        return !cnpjsExistentes.contains(cnpj);
    }

    public void adicionarCnpjExistente(int cnpj) {
        cnpjsExistentes.add(cnpj);
    }

    public boolean validarOficina(Oficina oficina) {
        return validaCnpj(oficina.getCnpjOficina());
    }
}
