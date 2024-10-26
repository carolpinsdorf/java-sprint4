package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = Acesso.TABLE_ENTIDADE)
public class Acesso extends _EntidadeBase {
    public static final String TABLE_ENTIDADE = "T_ACESSO";

    @Column(name = "email_acesso", nullable = false, unique = true, length = 100)
    private String emailAcesso;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "senha", nullable = false, length = 20)
    private String senha;

    @Column(name = "situacao", length = 10)
    private String situacao;

    @Column(name = "data_cadastro")
    private Date dataCadastro;


    public Acesso() {
    }

    public Acesso(int id, String emailAcesso, String username, String senha, String situacao, Date dataCadastro) {
        super(id);
        this.emailAcesso = emailAcesso;
        this.username = username;
        this.senha = senha;
        this.situacao = situacao;
        this.dataCadastro = dataCadastro;
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
}
