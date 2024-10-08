package org.example.entities;

public class Dtc extends _EntidadeBase{
    private Long idDtc;
    private String codDtc;
    private String descricao;
    public Dtc(){}

    public Dtc(Long idDtc, String codDtc, String descricao) {
        this.idDtc = idDtc;
        this.codDtc = codDtc;
        this.descricao = descricao;
    }

    public Dtc(Long id, Long idDtc, String codDtc, String descricao) {
        super(id);
        this.idDtc = idDtc;
        this.codDtc = codDtc;
        this.descricao = descricao;
    }

    public Long getIdDtc() {
        return idDtc;
    }

    public void setIdDtc(Long idDtc) {
        this.idDtc = idDtc;
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

