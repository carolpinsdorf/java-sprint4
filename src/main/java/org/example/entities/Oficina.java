package org.example.entities;

public class Oficina extends _EntidadeBase {
    private Long idOficina;
    private int cnpjOficina;
    private Long fkAcesso;

    public Oficina(){}

    public Oficina(Long idOficina, int cnpjOficina, Long fkAcesso) {
        this.idOficina = idOficina;
        this.cnpjOficina = cnpjOficina;
        this.fkAcesso = fkAcesso;
    }

    public Oficina(Long id, Long idOficina, int cnpjOficina, Long fkAcesso) {
        super(id);
        this.idOficina = idOficina;
        this.cnpjOficina = cnpjOficina;
        this.fkAcesso = fkAcesso;
    }

    public Long getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    public int getCnpjOficina() {
        return cnpjOficina;
    }

    public void setCnpjOficina(int cnpjOficina) {
        this.cnpjOficina = cnpjOficina;
    }

    public Long getFkAcesso() {
        return fkAcesso;
    }

    public void setFkAcesso(Long fkAcesso) {
        this.fkAcesso = fkAcesso;
    }
}

