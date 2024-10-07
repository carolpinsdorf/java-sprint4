package org.example.entities;

public class Servico extends _EntidadeBase {
    private String nomeServico;
    private double preco;

    public Servico(){
        super();
    }
    public Servico(String nomeServico, double preco) {
        this.nomeServico = nomeServico;
        this.preco = preco;
    }

    public Servico(Long id, String nomeServico, double preco) {
        super(id);
        this.nomeServico = nomeServico;
        this.preco = preco;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void atualizarPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    public String obterDetalhesServico() {
        return "Serviço: " + nomeServico + ", Preço: R$" + preco;
    }

    public boolean isPrecoAcima(double limite) {
        return this.preco > limite;
    }

    public void aplicarPromocao(double desconto) {
        this.preco -= this.preco * desconto / 100;
    }

    public void atualizarNomeServico(String novoNome) {
        this.nomeServico = novoNome;
    }
}
