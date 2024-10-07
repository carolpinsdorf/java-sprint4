package org.example.services;

import org.example.entities.Acesso;

import java.util.regex.Pattern;

public class AcessoValidator extends _BaseEntityValidatorImpl<Acesso>{

    public boolean validaUsername(String usersame){
        String username = Acesso.getUsername();
        String regexUsername = "^[a-zA-Z0-9]{4,}$";

        return Pattern.matches(regexUsername, username);
    }

    public boolean validaSenha(String senha){
        String password = Acesso.getPassword();
        String regexPassword = "^.{6,}$";

        return Pattern.matches(regexPassword, password);
    }

}
