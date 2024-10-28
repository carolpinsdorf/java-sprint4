package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_DTC")
public class Dtc extends _EntidadeBase {

    @Column(name = "cod_dtc", nullable = false, length = 5)
    private String codDtc;

    @Column(name = "descricao", nullable = false, columnDefinition = "LONG")
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

    // Getters e Setters
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

    @Override
    public String toString() {
        return "Dtc{" +
                "codDtc='" + codDtc + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

