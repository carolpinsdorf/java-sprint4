package org.example.entities;

public class Dtc extends _EntidadeBase{
    private String codDtc;
    private String descricao;


    public Dtc() {
    }

    public Dtc(String codDtc, String descricao) {
        this.codDtc = codDtc;
        this.descricao = descricao;
    }

    public Dtc(int id, String codDtc, String descricao) {
        super(id);
        this.codDtc = codDtc;
        this.descricao = descricao;
    }

    public String getCodDtc() {
        return codDtc;
    }

    public void setCodDtc(String codDtc) {
        this.codDtc = codDtc;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

