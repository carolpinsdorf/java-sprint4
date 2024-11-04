package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_DIAGNOSTICO")
public class Diagnostico extends _EntidadeBase {

    @Column(name = "desc_diagnostico", nullable = false)
    private String descDiagnostico;

    @ManyToOne
    @JoinColumn(name = "fk_servico", nullable = false)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "fk_id_dtc", nullable = false)
    private Dtc dtc;

    public Diagnostico() {
    }

    public Diagnostico(String descDiagnostico, Servico servico, Dtc dtc) {
        this.descDiagnostico = descDiagnostico;
        this.servico = servico;
        this.dtc = dtc;
    }

    public Diagnostico(int id, String descDiagnostico, Servico servico, Dtc dtc) {
        super(id);
        this.descDiagnostico = descDiagnostico;
        this.servico = servico;
        this.dtc = dtc;
    }

    public String getDescDiagnostico() {
        return descDiagnostico;
    }

    public void setDescDiagnostico(String descDiagnostico) {
        this.descDiagnostico = descDiagnostico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Dtc getDtc() {
        return dtc;
    }

    public void setDtc(Dtc dtc) {
        this.dtc = dtc;
    }

    @Override
    public String toString() {
        return "Diagnostico{" +
                "descDiagnostico='" + descDiagnostico + '\'' +
                ", servico=" + servico +
                ", dtc=" + dtc +
                '}';
    }

}

