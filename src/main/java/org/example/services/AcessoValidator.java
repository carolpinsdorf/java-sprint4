package org.example.services;

import org.example.entities.Acesso;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class AcessoValidator extends _BaseEntityValidatorImpl<Acesso> {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private final Set<String> emailsExistentes = new HashSet<>();
    private final Set<String> usernamesExistentes = new HashSet<>();

    public boolean validaEmail(String email) {
        if (!validaCampoObg(email)) return false;
        if (!Pattern.matches(EMAIL_REGEX, email)) return false;
        return !emailsExistentes.contains(email);
    }

    public boolean validaUsername(String username) {
        if (!validaCampoObg(username)) return false;
        if (username.length() < 12) return false;
        return !usernamesExistentes.contains(username);
    }

    public boolean validaSenha(String senha) {
        return validaCampoObg(senha) && senha.length() <= 10;
    }


    public void adicionarAcessoExistente(String email, String username) {
        emailsExistentes.add(email);
        usernamesExistentes.add(username);
    }

    public boolean validarAcesso(Acesso acesso) {
        return validaEmail(acesso.getEmailAcesso()) &&
                validaUsername(acesso.getUsername()) &&
                validaSenha(acesso.getSenha());
    }
}
