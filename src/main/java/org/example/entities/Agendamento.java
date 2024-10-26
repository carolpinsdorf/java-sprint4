package org.example.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_AGENDAMENTO")
public class Agendamento extends _EntidadeBase {

    @Column(name = "dthora_agendamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dthoraAgendamento;

    @Column(name = "status_agendamento", nullable = false, length = 10)
    private String statusAgendamento;

    @Column(name = "obs_agendamento", columnDefinition = "TEXT")
    private String obsAgendamento;

    @ManyToOne
    @JoinColumn(name = "fk_oficina")
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "fk_carro")
    private Carro carro;

    public Agendamento() {
    }

    public Agendamento(Date dthoraAgendamento, String statusAgendamento, String obsAgendamento, Oficina oficina, Carro carro) {
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.obsAgendamento = obsAgendamento;
        this.oficina = oficina;
        this.carro = carro;
    }

    // Construtor com ID
    public Agendamento(int id, Date dthoraAgendamento, String statusAgendamento, String obsAgendamento, Oficina oficina, Carro carro) {
        super(id);
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.obsAgendamento = obsAgendamento;
        this.oficina = oficina;
        this.carro = carro;
    }

    // Getters e Setters

    public Date getDthoraAgendamento() {
        return dthoraAgendamento;
    }

    public void setDthoraAgendamento(Date dthoraAgendamento) {
        this.dthoraAgendamento = dthoraAgendamento;
    }

    public String getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(String statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }

    public String getObsAgendamento() {
        return obsAgendamento;
    }

    public void setObsAgendamento(String obsAgendamento) {
        this.obsAgendamento = obsAgendamento;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
