package org.example.entities;

import java.util.Date;

public class Carro extends _EntidadeBase {
    private Long idCarro;
    private String placa;
    private String modelo;
    private String marca;
    private int anoFabricacao;
    private Integer torque;
    private String cambio;
    private String combustivel;
    private String cor;
    private Integer quilometragem;
    private Long fkCliente;

    public Carro() {}

    public Carro(Long idCarro, String placa, String modelo, String marca, int anoFabricacao, Integer torque, String cambio, String combustivel, String cor, Integer quilometragem, Long fkCliente) {
        this.idCarro = idCarro;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.torque = torque;
        this.cambio = cambio;
        this.combustivel = combustivel;
        this.cor = cor;
        this.quilometragem = quilometragem;
        this.fkCliente = fkCliente;
    }

    public Carro(Long id, Long idCarro, String placa, String modelo, String marca, int anoFabricacao, Integer torque, String cambio, String combustivel, String cor, Integer quilometragem, Long fkCliente) {
        super(id);
        this.idCarro = idCarro;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.torque = torque;
        this.cambio = cambio;
        this.combustivel = combustivel;
        this.cor = cor;
        this.quilometragem = quilometragem;
        this.fkCliente = fkCliente;
    }

    public Long getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Long idCarro) {
        this.idCarro = idCarro;
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

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Long getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Long fkCliente) {
        this.fkCliente = fkCliente;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean verificarPlaca(String placa) {
        return this.placa.equalsIgnoreCase(placa);
    }


    public int calcularIdadeCarro() {
        return new Date().getYear() + 1900 - this.anoFabricacao;
    }

    public void atualizarModelo(String novoModelo) {
        this.modelo = novoModelo;
    }

    public void atualizarPlaca(String novaPlaca) {
        this.placa = novaPlaca;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "fkCliente=" + fkCliente +
                ", idCarro=" + idCarro +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", torque=" + torque +
                ", cambio='" + cambio + '\'' +
                ", combustivel='" + combustivel + '\'' +
                ", cor='" + cor + '\'' +
                ", quilometragem=" + quilometragem +
                '}';
    }
}

