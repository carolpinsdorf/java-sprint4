package org.example.entities;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "T_AGENDAMENTO")
public class Agendamento extends _EntidadeBase {

    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "dthora_agendamento", nullable = false)
    private LocalDateTime dthoraAgendamento;

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

    public Agendamento(LocalDateTime dthoraAgendamento, String statusAgendamento, String obsAgendamento, Oficina oficina, Carro carro) {
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.obsAgendamento = obsAgendamento;
        this.oficina = oficina;
        this.carro = carro;
    }

    public Agendamento(int id, LocalDateTime dthoraAgendamento, String statusAgendamento, String obsAgendamento, Oficina oficina, Carro carro) {
        super(id);
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.obsAgendamento = obsAgendamento;
        this.oficina = oficina;
        this.carro = carro;
    }

    public LocalDateTime getDthoraAgendamento() {
        return dthoraAgendamento;
    }

    public void setDthoraAgendamento(LocalDateTime dthoraAgendamento) {
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

    @Override
    public String toString() {
        return "Agendamento{" +
                "dthoraAgendamento=" + dthoraAgendamento +
                ", statusAgendamento='" + statusAgendamento + '\'' +
                ", obsAgendamento='" + obsAgendamento + '\'' +
                ", oficina=" + oficina +
                ", carro=" + carro +
                '}';
    }
    public void cancelarAgendamento(String motivoCancelamento) {
        this.statusAgendamento = "cancelado";
        this.obsAgendamento = (obsAgendamento == null ? "" : obsAgendamento + "\n") +
                "Cancelado em: " + LocalDateTime.now() +
                " - Motivo: " + motivoCancelamento;
    }

    public boolean reagendar(LocalDateTime novaDataHora) {
        if (novaDataHora.isAfter(LocalDateTime.now())) {
            this.dthoraAgendamento = novaDataHora;
            this.statusAgendamento = "reagendado";
            return true;
        }
        return false;
    }

}
