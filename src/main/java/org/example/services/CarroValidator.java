package org.example.services;

import org.example.entities.Carro;

import java.util.HashSet;
import java.util.Set;

public class CarroValidator extends _BaseEntityValidatorImpl<Carro> {

    private final Set<String> placasExistentes = new HashSet<>();

    private static final String REGEX_PLACA = "^[A-Z]{3}\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$";

    public boolean validaPlaca(String placa) {
        if (placa == null || !placa.matches(REGEX_PLACA)) {
            return false;
        }
        return !placasExistentes.contains(placa);
    }

    public void adicionarPlacaExistente(String placa) {
        placasExistentes.add(placa);
    }

    @Override
    public boolean validaAno(int ano) {
        return ano >= 1886 && ano <= new java.util.Date().getYear() + 1900;
    }

    public boolean validarCarro(Carro carro) {
        boolean placaValida = validaPlaca(carro.getPlaca());
        boolean anoValido = validaAno(carro.getAnoFabricacao());
        return placaValida && anoValido;
    }
}
