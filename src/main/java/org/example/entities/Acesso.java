package org.example.entities;

import java.util.Date;

public class Acesso extends _EntidadeBase{
    private Long idAcesso;
    private String emailAcesso;
    private String username;
    private String senha;
    private String situacao;
    private Date dataCadastro;


    public Acesso() {

    }

    public Acesso(Long idAcesso, String emailAcesso, String username, String senha, String situacao, Date dataCadastro) {
        this.idAcesso = idAcesso;
        this.emailAcesso = emailAcesso;
        this.username = username;
        this.senha = senha;
        this.situacao = situacao;
        this.dataCadastro = dataCadastro;
    }

    public Acesso(Long id, Long idAcesso, String emailAcesso, String username, String senha, String situacao, Date dataCadastro) {
        super(id);
        this.idAcesso = idAcesso;
        this.emailAcesso = emailAcesso;
        this.username = username;
        this.senha = senha;
        this.situacao = situacao;
        this.dataCadastro = dataCadastro;
    }

    public Long getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Long idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getEmailAcesso() {
        return emailAcesso;
    }

    public void setEmailAcesso(String emailAcesso) {
        this.emailAcesso = emailAcesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static String getUsername() {
        return getUsername();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPassword() {
        return getPassword();
    }

    public void setPassword(String password) {
        this.senha = password;
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }


    public boolean verificarUsuario(String usuario) {
        return this.username.equals(usuario);
    }

    public void redefinirSenha() {
        this.senha = "default";
    }

    @Override
    public String toString() {
        return "Acesso{" +
                "idAcesso=" + idAcesso +
                ", emailAcesso='" + emailAcesso + '\'' +
                ", username='" + username + '\'' +
                ", senha='" + senha + '\'' +
                ", situacao='" + situacao + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}

