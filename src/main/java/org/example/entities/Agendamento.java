package org.example.entities;

import java.util.Date;

public class Agendamento extends _EntidadeBase {
    private Date dthoraAgendamento;
    private String statusAgendamento;
    private Oficina oficina;
    private Carro carro;

    public Agendamento() {
    }

    public Agendamento(Date dthoraAgendamento, String statusAgendamento, Oficina oficina, Carro carro) {
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.oficina = oficina;
        this.carro = carro;
    }

    public Agendamento(int id, Date dthoraAgendamento, String statusAgendamento, Oficina oficina, Carro carro) {
        super(id);
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.oficina = oficina;
        this.carro = carro;
    }

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

