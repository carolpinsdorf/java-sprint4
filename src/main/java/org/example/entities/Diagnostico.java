package org.example.entities;

public class Diagnostico extends _EntidadeBase {
    private String descDiagnostico;
    private Servico servico;
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
}

