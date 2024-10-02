package org.example.entitities;

public class Diagnostico extends _EntidadeBase {
    private String descricaoProblema;
    private String solucaoProposta;
    private double custoEstimado;

    public Diagnostico(){
        super();
    }

    public Diagnostico(String descricaoProblema, String solucaoProposta, double custoEstimado) {
        this.descricaoProblema = descricaoProblema;
        this.solucaoProposta = solucaoProposta;
        this.custoEstimado = custoEstimado;
    }

    public Diagnostico(Long id, String descricaoProblema, String solucaoProposta, double custoEstimado) {
        super(id);
        this.descricaoProblema = descricaoProblema;
        this.solucaoProposta = solucaoProposta;
        this.custoEstimado = custoEstimado;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getSolucaoProposta() {
        return solucaoProposta;
    }

    public void setSolucaoProposta(String solucaoProposta) {
        this.solucaoProposta = solucaoProposta;
    }

    public double getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(double custoEstimado) {
        this.custoEstimado = custoEstimado;
    }
    public void atualizarSolucao(String novaSolucao) {
        this.solucaoProposta = novaSolucao;
    }

    public void atualizarCusto(double novoCusto) {
        this.custoEstimado = novoCusto;
    }

    public String obterDiagnosticoCompleto() {
        return "Problema: " + descricaoProblema + ", Solução: " + solucaoProposta + ", Custo: " + custoEstimado;
    }

    public boolean isCustoAlto(double limite) {
        return this.custoEstimado > limite;
    }

    public void atualizarDescricaoProblema(String novaDescricao) {
        this.descricaoProblema = novaDescricao;
    }
}

