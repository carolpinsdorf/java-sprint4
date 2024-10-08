package org.example.entities;
import java.util.Date;

public class Agendamento extends _EntidadeBase {
    private Long idAgendamento;
    private Date dthoraAgendamento;
    private String statusAgendamento;
    private String obsAgendamento;
    private Long fkOficina;
    private Long fkCarro;

    public Agendamento() {}

    public Agendamento(Long idAgendamento, Date dthoraAgendamento, String statusAgendamento, String obsAgendamento, Long fkOficina, Long fkCarro) {
        this.idAgendamento = idAgendamento;
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.obsAgendamento = obsAgendamento;
        this.fkOficina = fkOficina;
        this.fkCarro = fkCarro;
    }

    public Agendamento(Long id, Long idAgendamento, Date dthoraAgendamento, String statusAgendamento, String obsAgendamento, Long fkOficina, Long fkCarro) {
        super(id);
        this.idAgendamento = idAgendamento;
        this.dthoraAgendamento = dthoraAgendamento;
        this.statusAgendamento = statusAgendamento;
        this.obsAgendamento = obsAgendamento;
        this.fkOficina = fkOficina;
        this.fkCarro = fkCarro;
    }

    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
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

    public String getObsAgendamento() {
        return obsAgendamento;
    }

    public void setObsAgendamento(String obsAgendamento) {
        this.obsAgendamento = obsAgendamento;
    }

    public Long getFkOficina() {
        return fkOficina;
    }

    public void setFkOficina(Long fkOficina) {
        this.fkOficina = fkOficina;
    }

    public Long getFkCarro() {
        return fkCarro;
    }

    public void setFkCarro(Long fkCarro) {
        this.fkCarro = fkCarro;
    }

    public boolean reagendar(Date novaData) {
        if (novaData.after(new Date())) {
            this.dthoraAgendamento = novaData;
            return true;
        }
        return false;
    }

    public long diasAteAgendamento() {
        long diff = dthoraAgendamento.getTime() - (new Date()).getTime();
        return diff / (1000 * 60 * 60 * 24);
    }


    //public boolean estaNoPrazo() {
    //    return statusAgendamento.after(new Date());
   // }

    public void atualizarDescricao(String novaDescricao) {
        this.obsAgendamento = novaDescricao;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "idAgendamento=" + idAgendamento +
                ", dthoraAgendamento=" + dthoraAgendamento +
                ", statusAgendamento='" + statusAgendamento + '\'' +
                ", obsAgendamento='" + obsAgendamento + '\'' +
                ", fkOficina=" + fkOficina +
                ", fkCarro=" + fkCarro +
                '}';
    }
}

