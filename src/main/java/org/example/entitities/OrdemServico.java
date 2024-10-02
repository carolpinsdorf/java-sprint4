package org.example.entitities;

public class OrdemServico extends _EntidadeBase {
    private String descricaoServico;
    private double valorTotal;

    public OrdemServico(){
        super();
    }


    public OrdemServico(String descricaoServico, double valorTotal) {
        this.descricaoServico = descricaoServico;
        this.valorTotal = valorTotal;
    }

    public OrdemServico(Long id, String descricaoServico, double valorTotal) {
        super(id);
        this.descricaoServico = descricaoServico;
        this.valorTotal = valorTotal;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public void atualizarValor(double novoValor) {
        this.valorTotal = novoValor;
    }

    public void atualizarDescricao(String novaDescricao) {
        this.descricaoServico = novaDescricao;
    }

    public boolean isValorAcima(double limite) {
        return this.valorTotal > limite;
    }

    public String obterResumoOrdem() {
        return "Serviço: " + descricaoServico + ", Valor Total: R$" + valorTotal;
    }

    public void aplicarDesconto(double percentual) {
        this.valorTotal -= this.valorTotal * percentual / 100;
    }
}
