package org.example.entities;

public class Cliente extends _EntidadeBase {
    private String nome;
    private String telefone;
    private String cpf;

    public Cliente(){
        super();
    }

    public Cliente(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Cliente(Long id, String nome, String telefone, String cpf) {
        super(id);
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String obterDetalhesCliente() {
        return "Cliente: " + nome + ", Telefone: " + telefone;
    }

    public boolean verificarNome(String nome) {
        return this.nome.equalsIgnoreCase(nome);
    }

    public void atualizarTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    public void atualizarNome(String novoNome) {
        this.nome = novoNome;
    }

    public boolean verificarTelefone(String telefone) {
        return this.telefone.equals(telefone);
    }
}
