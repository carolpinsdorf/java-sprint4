package org.example.services;

import org.example.entities.Endereco;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class EnderecoValidator extends _BaseEntityValidatorImpl<Endereco> {

    private static final Set<Integer> cepsCadastrados = new HashSet<>();

    private static final String CEP_REGEX = "^[0-9]{5}-?[0-9]{3}$";

    @Override
    public boolean validaCampoObg(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }

    public boolean validaCepUnico(int cep) {
        return cepsCadastrados.add(cep); // Retorna true se o CEP foi adicionado, ou seja, é único
    }

    public boolean validaCepFormato(String cep) {
        return Pattern.matches(CEP_REGEX, cep);
    }
}
