package org.example.entities;

public class Diagnostico extends _EntidadeBase {
    private Long idDiagnostico;
    private String descDiagnostico;
    private Long fkServico;
    private Long fkDtc;


    public Diagnostico(){}

    public Diagnostico(Long idDiagnostico, String descDiagnostico, Long fkServico, Long fkDtc) {
        this.idDiagnostico = idDiagnostico;
        this.descDiagnostico = descDiagnostico;
        this.fkServico = fkServico;
        this.fkDtc = fkDtc;
    }

    public Diagnostico(Long id, Long idDiagnostico, String descDiagnostico, Long fkServico, Long fkDtc) {
        super(id);
        this.idDiagnostico = idDiagnostico;
        this.descDiagnostico = descDiagnostico;
        this.fkServico = fkServico;
        this.fkDtc = fkDtc;
    }

    public Long getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getDescDiagnostico() {
        return descDiagnostico;
    }

    public void setDescDiagnostico(String descDiagnostico) {
        this.descDiagnostico = descDiagnostico;
    }

    public Long getFkServico() {
        return fkServico;
    }

    public void setFkServico(Long fkServico) {
        this.fkServico = fkServico;
    }

    public Long getFkDtc() {
        return fkDtc;
    }

    public void setFkDtc(Long fkDtc) {
        this.fkDtc = fkDtc;
    }
}

