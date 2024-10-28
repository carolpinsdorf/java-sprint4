package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_SERVICO")
public class Servico extends _EntidadeBase {

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private float valor;

    @Column(name = "tempo_execucao", nullable = false)
    private String tempoExecucao;

    @Enumerated(EnumType.STRING) // Mapeando a enumeração como uma string no banco de dados
    @Column(name = "status_servico") // Aqui você pode adicionar a coluna correspondente ao status, se desejado
    private StatusServico statusServico;

    @ManyToOne
    @JoinColumn(name = "fk_agendamento", nullable = false)
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

    // Getters e Setters
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

    public void confirmarServico() {
        if (statusServico.isPendente()) {
            this.statusServico = StatusServico.CONFIRMADO;
        } else {
            throw new IllegalStateException("Apenas serviços pendentes podem ser confirmados.");
        }
    }

    public void iniciarServico() {
        if (statusServico.podeIniciar()) {
            this.statusServico = StatusServico.EM_ANDAMENTO;
        } else {
            throw new IllegalStateException("Serviço não pode ser iniciado no status atual.");
        }
    }

    public String getDescricaoStatus() {
        return statusServico.obterDescricaoStatus();
    }

    @Override
    public String toString() {
        return "Servico{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tempoExecucao='" + tempoExecucao + '\'' +
                ", statusServico=" + statusServico +
                ", agendamento=" + agendamento +
                '}';
    }
}
