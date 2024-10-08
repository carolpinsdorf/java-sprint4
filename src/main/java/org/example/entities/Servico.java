package org.example.entities;

public class Servico extends _EntidadeBase {
    private String nomeServico;
    private double preco;
    private StatusServico status;

    public Servico() {
        super();
        this.status = StatusServico.PENDENTE;
    }

    public Servico(String nomeServico, double preco) {
        this();
        this.nomeServico = nomeServico;
        this.preco = preco;
    }

    public Servico(Long id, String nomeServico, double preco, StatusServico status) {
        super(id);
        this.nomeServico = nomeServico;
        this.preco = preco;
        this.status = status; // Inicializa o status
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

    public StatusServico getStatus() { // Novo getter
        return status;
    }

    public void setStatus(StatusServico status) { // Novo setter
        this.status = status;
    }

    // Outros métodos...

    public void atualizarPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    public String obterDetalhesServico() {
        return "Serviço: " + nomeServico + ", Preço: R$" + preco + ", Status: " + status.obterDescricaoStatus();
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
