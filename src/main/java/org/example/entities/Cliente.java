package org.example.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_CLIENTE")
public class Cliente extends _EntidadeBase {

    @Column(name = "cpf_cliente", nullable = false, unique = true)
    private Long cpfCliente;

    @Column(name = "nm_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "rg_cliente", length = 10)
    private String rgCliente;

    @Column(name = "dt_nascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "sx_cliente", length = 20)
    private String sexoCliente;

    @Column(name = "estado_civil", length = 20)
    private String estadoCivil;

    @ManyToOne
    @JoinColumn(name = "fk_acesso")
    private Acesso acesso;


    public Cliente() {
    }

    public Cliente(Long cpfCliente, String nomeCliente, String rgCliente, Date dataNascimento, String sexoCliente, String estadoCivil, Acesso acesso) {
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.rgCliente = rgCliente;
        this.dataNascimento = dataNascimento;
        this.sexoCliente = sexoCliente;
        this.estadoCivil = estadoCivil;
        this.acesso = acesso;
    }

    public Cliente(int id, Long cpfCliente, String nomeCliente, String rgCliente, Date dataNascimento, String sexoCliente, String estadoCivil, Acesso acesso) {
        super(id);
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.rgCliente = rgCliente;
        this.dataNascimento = dataNascimento;
        this.sexoCliente = sexoCliente;
        this.estadoCivil = estadoCivil;
        this.acesso = acesso;
    }

    // Getters and setters
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

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }
}
