package org.example.entities;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "T_CLIENTE")
public class Cliente extends _EntidadeBase {

    @Column(name = "cpf_cliente", nullable = false, unique = true)
    private Long cpfCliente;

    @Column(name = "nm_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "rg_cliente", nullable = true, length = 20)
    private String rgCliente;

    @Column(name = "dt_nascimento", nullable = false)
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Column(name = "sx_cliente", nullable = true, length = 10)
    private String sexoCliente;

    @Column(name = "estado_civil", nullable = true, length = 15)
    private String estadoCivil;

    @ManyToOne
    @JoinColumn(name = "fk_acesso")
    private Acesso acesso;


    public Cliente() {
    }

    public Cliente(Long cpfCliente, String nomeCliente, String rgCliente, LocalDate dataNascimento, String sexoCliente, String estadoCivil, Acesso acesso) {
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.rgCliente = rgCliente;
        this.dataNascimento = dataNascimento;
        this.sexoCliente = sexoCliente;
        this.estadoCivil = estadoCivil;
        this.acesso = acesso;
    }

    public Cliente(int id, Long cpfCliente, String nomeCliente, String rgCliente, LocalDate dataNascimento, String sexoCliente, String estadoCivil, Acesso acesso) {
        super(id);
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.rgCliente = rgCliente;
        this.dataNascimento = dataNascimento;
        this.sexoCliente = sexoCliente;
        this.estadoCivil = estadoCivil;
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

    public String getRgCliente() {
        return rgCliente;
    }

    public void setRgCliente(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
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

    @Override
    public String toString() {
        return "Cliente{" +
                "cpfCliente=" + cpfCliente +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", rgCliente='" + rgCliente + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexoCliente='" + sexoCliente + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", acesso=" + acesso +
                '}';
    }
}
