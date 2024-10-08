package org.example.entities;

import java.util.Date;

public class Cliente extends _EntidadeBase {
    private Long idCliente;
    private Long cpfCliente;
    private String nomeCliente;
    private String rgCliente;
    private Date dataNascimento;
    private String sexoCliente;
    private String estadoCivil;
    private Long fkAcesso;

    public Cliente(){}

    public Cliente(Long idCliente, Long cpfCliente, String nomeCliente, String rgCliente, Date dataNascimento, String sexoCliente, String estadoCivil, Long fkAcesso) {
        this.idCliente = idCliente;
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.rgCliente = rgCliente;
        this.dataNascimento = dataNascimento;
        this.sexoCliente = sexoCliente;
        this.estadoCivil = estadoCivil;
        this.fkAcesso = fkAcesso;
    }

    public Cliente(Long id, Long idCliente, Long cpfCliente, String nomeCliente, String rgCliente, Date dataNascimento, String sexoCliente, String estadoCivil, Long fkAcesso) {
        super(id);
        this.idCliente = idCliente;
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.rgCliente = rgCliente;
        this.dataNascimento = dataNascimento;
        this.sexoCliente = sexoCliente;
        this.estadoCivil = estadoCivil;
        this.fkAcesso = fkAcesso;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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

    public String getRgCliente() {
        return rgCliente;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Long getFkAcesso() {
        return fkAcesso;
    }

    public void setFkAcesso(Long fkAcesso) {
        this.fkAcesso = fkAcesso;
    }
}
