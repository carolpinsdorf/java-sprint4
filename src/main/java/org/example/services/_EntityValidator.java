package org.example.services;

public interface _EntityValidator<T> {

    boolean validaCampoObg(String campo);
    boolean validaNumPositivo(double numero);
    boolean validaAno(int ano);
    boolean validaNumero(String numero);
}
