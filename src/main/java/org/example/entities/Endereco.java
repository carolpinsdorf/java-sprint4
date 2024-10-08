package org.example.entities;

public class Endereco extends _EntidadeBase {
    private Long idEndereco;
    private int cepEndereco;
    private String logEndereco;
    private int numEndereco;
    private String cmplEndereco;
    private String bairro;
    private String cidade;
    private String estado;
    private Long fkCliente;
    private Long fkOficina;

    public Endereco(Long idEndereco, int cepEndereco, String logEndereco, int numEndereco, String cmplEndereco, String bairro, String cidade, String estado, Long fkCliente, Long fkOficina) {
        this.idEndereco = idEndereco;
        this.cepEndereco = cepEndereco;
        this.logEndereco = logEndereco;
        this.numEndereco = numEndereco;
        this.cmplEndereco = cmplEndereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.fkCliente = fkCliente;
        this.fkOficina = fkOficina;
    }

    public Endereco(Long id, Long idEndereco, int cepEndereco, String logEndereco, int numEndereco, String cmplEndereco, String bairro, String cidade, String estado, Long fkCliente, Long fkOficina) {
        super(id);
        this.idEndereco = idEndereco;
        this.cepEndereco = cepEndereco;
        this.logEndereco = logEndereco;
        this.numEndereco = numEndereco;
        this.cmplEndereco = cmplEndereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.fkCliente = fkCliente;
        this.fkOficina = fkOficina;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(int cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public String getLogEndereco() {
        return logEndereco;
    }

    public void setLogEndereco(String logEndereco) {
        this.logEndereco = logEndereco;
    }

    public int getNumEndereco() {
        return numEndereco;
    }

    public void setNumEndereco(int numEndereco) {
        this.numEndereco = numEndereco;
    }

    public String getCmplEndereco() {
        return cmplEndereco;
    }

    public void setCmplEndereco(String cmplEndereco) {
        this.cmplEndereco = cmplEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public Long getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Long fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Long getFkOficina() {
        return fkOficina;
    }

    public void setFkOficina(Long fkOficina) {
        this.fkOficina = fkOficina;
    }
}
