package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_CARRO")
public class Carro extends _EntidadeBase {

    @Column(name = "placa", nullable = false, unique = true, length = 7)
    private String placa;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "ano_fabricacao", nullable = false)
    private int anoFabricacao;

    @Column(name = "torque")
    private Integer torque;

    @Column(name = "cambio", length = 11)
    private String cambio;

    @Column(name = "combustivel", length = 20)
    private String combustivel;

    @Column(name = "cor", length = 20)
    private String cor;

    @Column(name = "quilometragem")
    private Integer quilometragem;

    @ManyToOne
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    // Construtor padrão
    public Carro() {
    }

    // Construtor com parâmetros sem o ID
    public Carro(String placa, String modelo, String marca, int anoFabricacao, Cliente cliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.cliente = cliente;
    }

    // Construtor com ID
    public Carro(int id, String placa, String modelo, String marca, int anoFabricacao, Cliente cliente) {
        super(id);
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.cliente = cliente;
    }

    // Getters e Setters
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

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
