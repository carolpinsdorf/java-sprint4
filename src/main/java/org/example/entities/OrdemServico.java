package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_ORDEM_SERVICO")
public class OrdemServico extends _EntidadeBase {

    @Column(name = "status_servico", nullable = false, length = 20)
    private String statusServico;

    @ManyToOne
    @JoinColumn(name = "fk_agendamento", nullable = false)
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
