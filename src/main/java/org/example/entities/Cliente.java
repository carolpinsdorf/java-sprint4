package org.example.entities;

import java.util.Date;

public class Cliente extends _EntidadeBase {
    private Long cpfCliente;
    private String nomeCliente;
    private Date dataNascimento;
    private Acesso acesso;

    public Cliente() {
    }

    public Cliente(Long cpfCliente, String nomeCliente, Date dataNascimento, Acesso acesso) {
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.acesso = acesso;
    }

    public Cliente(int id, Long cpfCliente, String nomeCliente, Date dataNascimento, Acesso acesso) {
        super(id);
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.acesso = acesso;
    }

    public Long getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(Long cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }
}
