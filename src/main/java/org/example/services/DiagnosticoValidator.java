package org.example.services;

import org.example.entities.Diagnostico;

public class DiagnosticoValidator extends _BaseEntityValidatorImpl<Diagnostico> {

    public boolean validar(Diagnostico diagnostico) {
        if (!validaCampoObg(diagnostico.getDescricaoProblema())) {
            System.out.println("A descrição do problema é obrigatória e não pode ser vazia.");
            return false;
        }

        if (!validaCampoObg(diagnostico.getSolucaoProposta())) {
            System.out.println("A solução proposta é obrigatória e não pode ser vazia.");
            return false;
        }

        if (!validaNumPositivo(diagnostico.getCustoEstimado())) {
            System.out.println("O custo estimado deve ser um número positivo.");
            return false;
        }

        if (!validaNumero(String.valueOf(diagnostico.getCustoEstimado()))) {
            System.out.println("O custo estimado deve ser numérico.");
            return false;
        }

        return true;
    }
}
