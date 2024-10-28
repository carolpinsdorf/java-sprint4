package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_ENDERECO")
public class Endereco extends _EntidadeBase {

    @Column(name = "cep_endereco", nullable = false, unique = true)
    private int cepEndereco;

    @Column(name = "log_endereco", nullable = false, length = 200)
    private String logEndereco;

    @Column(name = "num_endereco", nullable = false)
    private int numEndereco;

    @Column(name = "cmpl_endereco", length = 50)
    private String cmplEndereco;

    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_oficina")
    private Oficina oficina;

    public Endereco() {
    }

    // Construtor sem ID
    public Endereco(int cepEndereco, String logEndereco, int numEndereco, String cmplEndereco, String bairro, String cidade, String estado, Cliente cliente, Oficina oficina) {
        this.cepEndereco = cepEndereco;
        this.logEndereco = logEndereco;
        this.numEndereco = numEndereco;
        this.cmplEndereco = cmplEndereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
        this.oficina = oficina;
    }

    // Construtor com ID
    public Endereco(int id, int cepEndereco, String logEndereco, int numEndereco, String cmplEndereco, String bairro, String cidade, String estado, Cliente cliente, Oficina oficina) {
        super(id);
        this.cepEndereco = cepEndereco;
        this.logEndereco = logEndereco;
        this.numEndereco = numEndereco;
        this.cmplEndereco = cmplEndereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
        this.oficina = oficina;
    }

    // Getters e Setters

    public int getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(int cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public String getLogEndereco() {
        return logEndereco;
    }

    public void setLogEndereco(String logEndereco) {
        this.logEndereco = logEndereco;
    }

    public int getNumEndereco() {
        return numEndereco;
    }

    public void setNumEndereco(int numEndereco) {
        this.numEndereco = numEndereco;
    }

    public String getCmplEndereco() {
        return cmplEndereco;
    }

    public void setCmplEndereco(String cmplEndereco) {
        this.cmplEndereco = cmplEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cepEndereco=" + cepEndereco +
                ", logEndereco='" + logEndereco + '\'' +
                ", numEndereco=" + numEndereco +
                ", cmplEndereco='" + cmplEndereco + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cliente=" + cliente +
                ", oficina=" + oficina +
                '}';
    }
}
