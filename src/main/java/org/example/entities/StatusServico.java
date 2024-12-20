package org.example.entities;

public enum StatusServico {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDO,
    CANCELADO,
    CONFIRMADO;

    public boolean isFinalizado() {
        return this == CONCLUIDO || this == CANCELADO;
    }

    public String obterDescricaoStatus() {
        switch (this) {
            case PENDENTE: return "Serviço pendente";
            case EM_ANDAMENTO: return "Serviço em andamento";
            case CONCLUIDO: return "Serviço concluído";
            case CANCELADO: return "Serviço cancelado";
            case CONFIRMADO: return "Serviço confirmado";
            default: return "Status desconhecido";
        }
    }

    public boolean isEmProgresso() {
        return this == EM_ANDAMENTO || this == CONFIRMADO;
    }

    public boolean isPendente() {
        return this == PENDENTE;
    }

    public boolean podeIniciar() {
        return this == PENDENTE || this == CONFIRMADO;
    }

}


