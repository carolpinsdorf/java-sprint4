package org.example.entities;

public class Servico extends _EntidadeBase {
    private String descricao;
    private float valor;
    private String tempoExecucao;
    private StatusServico statusServico;
    private Agendamento agendamento;

    public Servico() {
    }

    public Servico(String descricao, float valor, String tempoExecucao, StatusServico statusServico, Agendamento agendamento) {
        this.descricao = descricao;
        this.valor = valor;
        this.tempoExecucao = tempoExecucao;
        this.statusServico = statusServico;
        this.agendamento = agendamento;
    }

    public Servico(int id, String descricao, float valor, String tempoExecucao, StatusServico statusServico, Agendamento agendamento) {
        super(id);
        this.descricao = descricao;
        this.valor = valor;
        this.tempoExecucao = tempoExecucao;
        this.statusServico = statusServico;
        this.agendamento = agendamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(String tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public StatusServico getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(StatusServico statusServico) {
        this.statusServico = statusServico;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}
