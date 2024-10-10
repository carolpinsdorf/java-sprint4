package org.example.entities;

import java.util.Date;

public class Acesso extends _EntidadeBase {
    private String emailAcesso;
    private String username;
    private String senha;

    public Acesso() {
    }

    public Acesso(String emailAcesso, String username, String senha) {
        this.emailAcesso = emailAcesso;
        this.username = username;
        this.senha = senha;
    }

    public Acesso(int id, Date dataCriacao, Date dataAtualizacao, String emailAcesso, String username, String senha) {
        super(id, dataCriacao, dataAtualizacao); // Esse construtor agora deve funcionar
        this.emailAcesso = emailAcesso;
        this.username = username;
        this.senha = senha;
    }

    public Acesso(int id, String emailAcesso, String username, String senha) {
        super(id); // Esse construtor deve funcionar também
        this.emailAcesso = emailAcesso;
        this.username = username;
        this.senha = senha;
    }

    public String getEmailAcesso() {
        return emailAcesso;
    }

    public void setEmailAcesso(String emailAcesso) {
        this.emailAcesso = emailAcesso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
