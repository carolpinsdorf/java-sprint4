package org.example.entitities;

import java.util.Date;

public class _EntidadeBase {
    protected Long id;
    protected Date dataCriacao;
    protected Date dataAtualizacao;


    public _EntidadeBase() {

    }

    public _EntidadeBase(Long id) {
        this.id = id;
        this.dataCriacao = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    public void atualizarDataAtualizacao() {
        this.dataAtualizacao = new Date();
    }

    public boolean isNovo() {
        return this.id == null;
    }

    public long calcularTempoExistente() {
        return (new Date()).getTime() - this.dataCriacao.getTime();
    }

    public String obterInformacoes() {
        return "ID: " + id + ", Criado em: " + dataCriacao;
    }

    public void atualizarId(Long novoId) {
        this.id = novoId;
    }
}
