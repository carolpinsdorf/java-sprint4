package org.example.services;

import org.example.entities.Diagnostico;

public class DiagnosticoValidator extends _BaseEntityValidatorImpl<Diagnostico> {

    public boolean validar(Diagnostico diagnostico) {
        if (!validaCampoObg(diagnostico.getDescDiagnostico())) {
            System.out.println("A descrição do diagnóstico é obrigatória e não pode ser vazia.");
            return false;
        }

        if (diagnostico.getFkServico() == null) {
            System.out.println("O diagnóstico deve estar relacionado a um serviço.");
            return false;
        }

        if (diagnostico.getFkDtc() == null) {
            System.out.println("O diagnóstico deve estar relacionado a um DTC.");
            return false;
        }

        return true;
    }
}
