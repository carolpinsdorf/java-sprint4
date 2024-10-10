package org.example.entities;

import java.util.Date;


public abstract class _EntidadeBase {
    private int id;
    private Date dataCriacao;
    private Date dataAtualizacao;


    public _EntidadeBase() {
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

    public _EntidadeBase(int id) {
        this.id = id;
        this.dataCriacao = new Date();
        this.dataAtualizacao = new Date();
    }

    public _EntidadeBase(int id, Date dataCriacao, Date dataAtualizacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
