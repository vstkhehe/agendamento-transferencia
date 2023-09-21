package com.avaliacao.tokiomarine.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Slf4j
public class CalculosUtil {

    public static String ERRO_RANGE_DATA = "Data de transferência fora da faixa da data do agendamento";
    public static double calculoPorcentagem(Double valorTransferencia){
        var porcentagem = (3.0/100.0) * valorTransferencia;
        return porcentagem;
    }

    public static boolean rangeDiasRegraB(String dataAgendamento, String dataTransferencia){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        var parseDataAgendamento = LocalDate.parse(dataAgendamento, formatter);
        var parseDataTransferencia = LocalDate.parse(dataTransferencia, formatter);

        if (parseDataTransferencia.isBefore(parseDataAgendamento.plusDays(11)))
            return true;
        else
            log.info(ERRO_RANGE_DATA);
            return false;
    }
}
