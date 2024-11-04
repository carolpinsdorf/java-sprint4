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
    public boolean isCodigoIgual(Dtc outroDtc) {
        if (outroDtc == null) return false;
        return this.codDtc.equalsIgnoreCase(outroDtc.getCodDtc());
    }
    public void atualizarDescricao(String novaDescricao, String justificativa) {
        if (novaDescricao != null && justificativa != null) {
            this.descricao = novaDescricao + " (Justificativa: " + justificativa + ")";
        }
    }
    public String getCodigoFormatado() {
        if (codDtc == null || codDtc.length() < 2) return codDtc;
        return codDtc.substring(0, 1) + "-" + codDtc.substring(1);
    }

}

