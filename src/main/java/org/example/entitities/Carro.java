package org.example.entitities;

import java.util.Date;

public class Carro extends _EntidadeBase {
    private Long id;
    private String modelo;
    private String placa;
    private int ano;
    private int quilometragem;
    private String marca;


    public Carro() {
        super();
    }

    public Carro(Long id, String modelo, String placa, int ano, int quilometragem, String marca) {
        super(id);
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.marca = marca;
    }

    public Carro(String modelo, String placa, int ano, int quilometragem, String marca) {
        this(null, modelo, placa, ano, quilometragem, marca);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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

    @Override
    public String toString() {
        return "Carro [Marca=" + marca +
                ", Modelo=" + modelo +
                ", Placa=" + placa +
                ", Ano=" + ano +
                ", Quilometragem=" + quilometragem + "]";
    }

    public int calcularIdadeCarro() {
        return new Date().getYear() + 1900 - this.ano;
    }

    public void atualizarModelo(String novoModelo) {
        this.modelo = novoModelo;
    }

    public void atualizarPlaca(String novaPlaca) {
        this.placa = novaPlaca;
    }
}

