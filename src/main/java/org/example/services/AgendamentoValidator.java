package org.example.services;

import org.example.entities.Agendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class AgendamentoValidator extends _BaseEntityValidatorImpl<Agendamento> {

    public boolean validaData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return false;
        }

        try {
            LocalDate.parse(data, DateTimeFormatter.ISO_DATE_TIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public boolean validaHora(String hora) {
        if (hora == null || hora.trim().isEmpty()) {
            return false;
        }

        String regexHora = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$";
        return Pattern.matches(regexHora, hora);
    }

    public boolean validaDescricaoServico(String descricao) {
        return validaCampoObg(descricao);
    }

    public boolean validaNumeroCliente(String numeroCliente) {
        return validaNumero(numeroCliente);
    }
}
