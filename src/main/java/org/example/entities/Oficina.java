package org.example.entities;

public class Oficina extends _EntidadeBase {
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;

    public Oficina(){
        super();
    }

    public Oficina(String nome, String cnpj, String telefone, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Oficina(Long id, String nome, String cnpj, String telefone, String endereco) {
        super(id);
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String obterInformacoesOficina() {
        return "Nome: " + nome + ", CNPJ: " + cnpj + ", Telefone: " + telefone;
    }

    public void atualizarTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    public boolean isCnpjValido() {
        return this.cnpj.length() == 14;
    }

    public void atualizarNome(String novoNome) {
        this.nome = novoNome;
    }

    public boolean isMesmoTelefone(String outroTelefone) {
        return this.telefone.equals(outroTelefone);
    }
}

