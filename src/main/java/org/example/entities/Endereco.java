package org.example.entities;

public class Endereco extends _EntidadeBase {
    private int cepEndereco;
    private String logEndereco;
    private int numEndereco;
    private String bairro;
    private String cidade;
    private String estado;
    private Cliente cliente;
    private Oficina oficina;


    public Endereco() {
    }

    public Endereco(int cepEndereco, String logEndereco, int numEndereco, String bairro, String cidade, String estado, Cliente cliente, Oficina oficina) {
        this.cepEndereco = cepEndereco;
        this.logEndereco = logEndereco;
        this.numEndereco = numEndereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
        this.oficina = oficina;
    }

    public Endereco(int id, int cepEndereco, String logEndereco, int numEndereco, String bairro, String cidade, String estado, Cliente cliente, Oficina oficina) {
        super(id);
        this.cepEndereco = cepEndereco;
        this.logEndereco = logEndereco;
        this.numEndereco = numEndereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
        this.oficina = oficina;
    }

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
}
