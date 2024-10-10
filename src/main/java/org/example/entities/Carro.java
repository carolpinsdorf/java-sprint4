package org.example.entities;

import java.util.Date;


public class Carro extends _EntidadeBase {
    private String placa;
    private String modelo;
    private String marca;
    private int anoFabricacao;
    private Cliente cliente;

    public Carro() {
    }

    public Carro(int id) {
        super(id);
    }

    public Carro(String placa, String modelo, String marca, int anoFabricacao, Cliente cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.cliente = cliente;
    }

    public Carro(int id, Date dataCriacao, Date dataAtualizacao, String placa, String modelo, String marca, int anoFabricacao, Cliente cliente) {
        super(id, dataCriacao, dataAtualizacao);
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.cliente = cliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}


