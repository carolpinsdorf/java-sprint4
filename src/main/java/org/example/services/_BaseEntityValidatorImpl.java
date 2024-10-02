package org.example.services;

public class _BaseEntityValidatorImpl<T> implements _EntityValidator<T> {


    @Override
    public boolean validaCampoObg(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }

    @Override
    public boolean validaNumPositivo(double numero) {
        return !(numero <= 0);
    }

    @Override
    public boolean validaAno(int ano) {
        return ano >= 1886 && ano <= new java.util.Date().getYear() + 1900;
    }

    @Override
    public boolean validaNumero(String numero) {
        // Verifica se é um número
        return numero != null && numero.matches("\\d+");
    }
}
