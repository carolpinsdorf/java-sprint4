package org.example.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

@Entity
@Table(name = "T_OFICINA")
public class Oficina extends _EntidadeBase {


    @Column(name = "cnpj_oficina", nullable = false, unique = true)
    private long cnpjOficina;

    @ManyToOne
    @JoinColumn(name = "fk_acesso")
    private Acesso acesso;

    public Oficina() {
    }

    public Oficina(long cnpjOficina, Acesso acesso) {
        this.cnpjOficina = cnpjOficina;
        this.acesso = acesso;
    }

    public Oficina(int id, long cnpjOficina, Acesso acesso) {
        super(id);
        this.cnpjOficina = cnpjOficina;
        this.acesso = acesso;
    }

    public long getCnpjOficina() {
        return cnpjOficina;
    }

    public void setCnpjOficina(long cnpjOficina) {
        this.cnpjOficina = cnpjOficina;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    @Override
    public String toString() {
        return "Oficina{" +
                "cnpjOficina=" + cnpjOficina +
                ", acesso=" + acesso +
                '}';
    }
    public void atualizarCnpj(long novoCnpj) {
        this.cnpjOficina = novoCnpj;
    }
    public String exibirCnpj() {
        return String.format("CNPJ da Oficina: %d", cnpjOficina);
    }
    public void associarAcesso(Acesso novoAcesso) {
        this.acesso = novoAcesso;
    }

}

