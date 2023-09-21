package com.avaliacao.tokiomarine.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Slf4j
public class CalculosUtil {
    private static Double porcentagem = 0.0;

    public static String ERRO_RANGE_DATA = "Data de transferência fora da faixa da data do agendamento";
    public static double regraTipoA(Double valorTransferencia){
        porcentagem = (3.0/100.0) * valorTransferencia;
        return porcentagem;
    }

    public static boolean rangeDiasRegraB(String dataAgendamento, String dataTransferencia){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        var parseDataAgendamento = LocalDate.parse(dataAgendamento, formatter);
        var parseDataTransferencia = LocalDate.parse(dataTransferencia, formatter);

        if (parseDataTransferencia.isBefore(parseDataAgendamento.plusDays(11)))
            return true;
        else
            return false;
    }

    public static Double rangeDiasRegraC(String dataAgendamento, String dataTransferencia, Double valorTransferencia){
        int identificadorRegra = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        var parseDataAgendamento = LocalDate.parse(dataAgendamento, formatter);
        var parseDataTransferencia = LocalDate.parse(dataTransferencia, formatter);

        if (parseDataTransferencia.isAfter(parseDataAgendamento.plusDays(11))
                && parseDataTransferencia.isBefore(parseDataAgendamento.plusDays(21)))
            identificadorRegra =  1;
        else
            log.info(ERRO_RANGE_DATA);


    Double result = null;
        switch (identificadorRegra) {
            case 1:
                result = (8.2/100.0) * valorTransferencia;
                break;
            /*case "CAT":
                result = "domestic animal";
                break;
            case "TIGER":
                result = "wild animal";
                break;
            default:
                result = "unknown animal";
                break;*/

        }

        return result;
    }
}
