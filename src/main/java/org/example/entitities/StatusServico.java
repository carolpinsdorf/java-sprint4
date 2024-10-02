package org.example.entitities;

public enum StatusServico {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDO,
    CANCELADO;

    public boolean isFinalizado() {
        return this == CONCLUIDO || this == CANCELADO;
    }

    public String obterDescricaoStatus() {
        switch (this) {
            case PENDENTE: return "Serviço pendente";
            case EM_ANDAMENTO: return "Serviço em andamento";
            case CONCLUIDO: return "Serviço concluído";
            case CANCELADO: return "Serviço cancelado";
            default: return "Status desconhecido";
        }
    }

    public boolean isEmProgresso() {
        return this == EM_ANDAMENTO;
    }

    public boolean isPendente() {
        return this == PENDENTE;
    }
    public boolean podeIniciar() {
        return this == PENDENTE;
    }
}

