package org.example.entities;

public class Oficina extends _EntidadeBase {
    private int cnpjOficina;
    private Acesso acesso;

    public Oficina() {
    }

    public Oficina(int id, int cnpjOficina, Acesso acesso) {
        super(id);
        this.cnpjOficina = cnpjOficina;
        this.acesso = acesso;
    }

    // Construtor que aceita apenas o ID
    public Oficina(int id) {
        super(id); // Passa o ID para a classe base
    }

    public int getCnpjOficina() {
        return cnpjOficina;
    }

    public void setCnpjOficina(int cnpjOficina) {
        this.cnpjOficina = cnpjOficina;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }
}
