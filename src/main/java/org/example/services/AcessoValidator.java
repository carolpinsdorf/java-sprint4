package org.example.services;

import org.example.entities.Acesso;

import java.util.regex.Pattern;

public class AcessoValidator extends _BaseEntityValidatorImpl<Acesso> {

    public boolean validaUsername(Acesso acesso) {
        String username = acesso.getUsername();  // Obtém o username da instância de Acesso
        String regexUsername = "^[a-zA-Z0-9]{4,}$";

        return Pattern.matches(regexUsername, username);
    }

    public boolean validaSenha(Acesso acesso) {
        String password = acesso.getPassword();  // Obtém a senha da instância de Acesso
        String regexPassword = "^.{6,}$";

        return Pattern.matches(regexPassword, password);
    }
}
