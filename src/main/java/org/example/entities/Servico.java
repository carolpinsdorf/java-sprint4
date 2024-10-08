package org.example.entities;

public class Servico extends _EntidadeBase {
    private Long idServico;
    private String descricao;
    private float valor;
    private String tempoExecucao;
    private StatusServico statusServico;
    private Long fkAgendamento;

    public Servico(Long idServico, String descricao, float valor, String tempoExecucao, StatusServico statusServico, Long fkAgendamento) {
        this.idServico = idServico;
        this.descricao = descricao;
        this.valor = valor;
        this.tempoExecucao = tempoExecucao;
        this.statusServico = statusServico;
        this.fkAgendamento = fkAgendamento;
    }

    public Servico(Long id, Long idServico, String descricao, float valor, String tempoExecucao, StatusServico statusServico, Long fkAgendamento) {
        super(id);
        this.idServico = idServico;
        this.descricao = descricao;
        this.valor = valor;
        this.tempoExecucao = tempoExecucao;
        this.statusServico = statusServico;
        this.fkAgendamento = fkAgendamento;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
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

    public Long getFkAgendamento() {
        return fkAgendamento;
    }

    public void setFkAgendamento(Long fkAgendamento) {
        this.fkAgendamento = fkAgendamento;
    }
}
