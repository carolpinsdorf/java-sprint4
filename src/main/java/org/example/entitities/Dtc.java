package org.example.entitities;

public class Dtc extends _EntidadeBase{
    private String codigo;
    private String descricao;

    public Dtc(){
        super();
    }
    public Dtc(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Dtc(Long id, String codigo, String descricao) {
        super(id);
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isCodigo(String codigo) {
        return this.codigo.equals(codigo);
    }

    public String obterDescricaoCompleta() {
        return "Código: " + codigo + ", Descrição: " + descricao;
    }

    public void atualizarDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }

    public void atualizarCodigo(String novoCodigo) {
        this.codigo = novoCodigo;
    }

    public String obterCodigoFormatado() {
        return "DTC-" + codigo;
    }
}

