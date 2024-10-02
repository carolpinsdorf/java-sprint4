package org.example.entitities;
import java.util.Date;

public class Agendamento extends _EntidadeBase {
    private Date dataAgendada;
    private String descricao;


    public Agendamento() {
        super();
    }

    public Agendamento(Date dataAgendada, String descricao) {
        this.dataAgendada = dataAgendada;
        this.descricao = descricao;
    }

    public Agendamento(Long id, Date dataAgendada, String descricao) {
        super(id);
        this.dataAgendada = dataAgendada;
        this.descricao = descricao;
    }


    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public boolean reagendar(Date novaData) {
        if (novaData.after(new Date())) {
            this.dataAgendada = novaData;
            return true;
        }
        return false;
    }

    public long diasAteAgendamento() {
        long diff = dataAgendada.getTime() - (new Date()).getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    public String obterDetalhesAgendamento() {
        return "Agendamento para: " + dataAgendada + ", Descrição: " + descricao;
    }

    public boolean estaNoPrazo() {
        return dataAgendada.after(new Date());
    }

    public void atualizarDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }

}

