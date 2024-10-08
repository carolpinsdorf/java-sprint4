package org.example.entities;

public class OrdemServico extends _EntidadeBase {
    private Long idOrdemServico;
    private String statusServico;
    private Long fkAgendamento;

    public OrdemServico(){
        super();
    }

    public OrdemServico(Long idOrdemServico, String statusServico, Long fkAgendamento) {
        this.idOrdemServico = idOrdemServico;
        this.statusServico = statusServico;
        this.fkAgendamento = fkAgendamento;
    }

    public OrdemServico(Long id, Long idOrdemServico, String statusServico, Long fkAgendamento) {
        super(id);
        this.idOrdemServico = idOrdemServico;
        this.statusServico = statusServico;
        this.fkAgendamento = fkAgendamento;
    }

    public Long getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(Long idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public String getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(String statusServico) {
        this.statusServico = statusServico;
    }

    public Long getFkAgendamento() {
        return fkAgendamento;
    }

    public void setFkAgendamento(Long fkAgendamento) {
        this.fkAgendamento = fkAgendamento;
    }
}
