package com.avaliacao.tokiomarine.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class CalculosUtil {
    private static Double porcentagem = 0.0;

    public static String ERRO_RANGE_DATA = "Data de transferência fora da faixa da data do agendamento";

    public static double regraTipoA(Double valorTransferencia) {
        porcentagem = (3.0 / 100.0) * valorTransferencia;
        return porcentagem;
    }

    public static boolean rangeDiasRegraB(String dataAgendamento, String dataTransferencia) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        var parseDataAgendamento = LocalDate.parse(dataAgendamento, formatter);
        var parseDataTransferencia = LocalDate.parse(dataTransferencia, formatter);

        if (parseDataTransferencia.isBefore(parseDataAgendamento.plusDays(11)))
            return true;
        else
            return false;
    }

    public static Double rangeDiasRegraC(String dataAgendamento, String dataTransferencia, Double valorTransferencia) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        var parseDataAgendamento = LocalDate.parse(dataAgendamento, formatter);
        var parseDataTransferencia = LocalDate.parse(dataTransferencia, formatter);

        if (parseDataTransferencia.isAfter(parseDataAgendamento.plusDays(10))
                && parseDataTransferencia.isBefore(parseDataAgendamento.plusDays(21))) {
            return switchCasePorcentagemRegraC(1, valorTransferencia);
        }
        if (parseDataTransferencia.isAfter(parseDataAgendamento.plusDays(20))
                && parseDataTransferencia.isBefore(parseDataAgendamento.plusDays(31))) {
            return switchCasePorcentagemRegraC(2, valorTransferencia);
        }
        if (parseDataTransferencia.isAfter(parseDataAgendamento.plusDays(30))
                && parseDataTransferencia.isBefore(parseDataAgendamento.plusDays(41))) {
            return switchCasePorcentagemRegraC(3, valorTransferencia);
        }
        if (parseDataTransferencia.isAfter(parseDataAgendamento.plusDays(40))) {
            return switchCasePorcentagemRegraC(4, valorTransferencia);
        } else {
            log.info(ERRO_RANGE_DATA);
        }
        return null;
    }

    private static Double switchCasePorcentagemRegraC(int identificadorRegra, Double valorTransferencia) {
        Double result = null;
        switch (identificadorRegra) {
            case 1:
                result = (8.2 / 100.0) * valorTransferencia;
                break;
            case 2:
                result = (6.9 / 100.0) * valorTransferencia;
                break;
            case 3:
                result = (4.7 / 100.0) * valorTransferencia;
                break;
            case 4:
                result = (1.7 / 100.0) * valorTransferencia;
                break;
            default:
                result = 0.0;
                log.info(ERRO_RANGE_DATA);
                break;
        }
        return result;
    }
}
