package org.example.entities;

public class OrdemServico extends _EntidadeBase {
    private String statusServico;
    private Agendamento agendamento;


    public OrdemServico() {
    }

    public OrdemServico(String statusServico, Agendamento agendamento) {
        this.statusServico = statusServico;
        this.agendamento = agendamento;
    }

    public OrdemServico(int id, String statusServico, Agendamento agendamento) {
        super(id);
        this.statusServico = statusServico;
        this.agendamento = agendamento;
    }

    public String getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(String statusServico) {
        this.statusServico = statusServico;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}
