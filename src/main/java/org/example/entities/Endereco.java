package org.example.entities;

public class Endereco extends _EntidadeBase {
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;

    public Endereco(){
        super();
    }

    public Endereco(String rua, String cidade, String estado, String cep, String numero) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.numero = numero;
    }

    public Endereco(Long id, String rua, String cidade, String estado, String cep, String numero) {
        super(id);
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String obterEnderecoCompleto() {
        return rua + ", " + cidade + ", " + estado + ", " + cep;
    }

    public void atualizarCidade(String novaCidade) {
        this.cidade = novaCidade;
    }

    public boolean isCepValido() {
        return cep.length() == 8;
    }

    public void atualizarEstado(String novoEstado) {
        this.estado = novoEstado;
    }

    public boolean isEnderecoIgual(Endereco outroEndereco) {
        return this.cep.equals(outroEndereco.getCep()) && this.rua.equals(outroEndereco.getRua());
    }
}
