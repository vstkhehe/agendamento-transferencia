package com.avaliacao.tokiomarine.util;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.avaliacao.tokiomarine.util.CalculosUtil.regraTipoA;

@Slf4j

//Classe responsável por construir o objeto após passar pelas regras e também por fazer os devidos cálculos de taxas.
public class RequestBuilderModelUtil {

    public static NumberFormat formatter = new DecimalFormat("#0,00");

    public static AgendamentoTransferenciaModel buildAgendamentoRegraTipoA(AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        return AgendamentoTransferenciaModel
                .builder()
                .id(agendamentoTransferenciaModel.getId() == 0 ? 0 : agendamentoTransferenciaModel.getId())
                .contaOrigem(agendamentoTransferenciaModel.getContaOrigem())
                .contaDestino(agendamentoTransferenciaModel.getContaDestino())
                .valorTransferencia(agendamentoTransferenciaModel.getValorTransferencia())
                .valorTransferenciaTaxa(Double.valueOf(formatter.format(3.0 + regraTipoA(agendamentoTransferenciaModel.getValorTransferencia()))))
                .valorTransferenciaTotal((Double.valueOf(formatter.format(agendamentoTransferenciaModel.getValorTransferencia()
                        + 3.0 + regraTipoA(agendamentoTransferenciaModel.getValorTransferencia())))))
                .dataTransferencia(agendamentoTransferenciaModel.getDataTransferencia())
                .build();
    }

    public static AgendamentoTransferenciaModel buildAgendamentoRegraTipoB(AgendamentoTransferenciaModel agendamentoTransferenciaModel){
       return AgendamentoTransferenciaModel
                .builder()
                .id(agendamentoTransferenciaModel.getId() == 0 ? 0 : agendamentoTransferenciaModel.getId())
                .contaOrigem(agendamentoTransferenciaModel.getContaOrigem())
                .contaDestino(agendamentoTransferenciaModel.getContaDestino())
                .valorTransferencia(agendamentoTransferenciaModel.getValorTransferencia())
                .valorTransferenciaTaxa(12.00)
                .valorTransferenciaTotal((Double.valueOf(formatter.format(agendamentoTransferenciaModel.getValorTransferencia() + 12.0))))
                .dataTransferencia(agendamentoTransferenciaModel.getDataTransferencia())
                .build();
    }

    public static AgendamentoTransferenciaModel buildAgendamentoRegraTipoC(AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        return AgendamentoTransferenciaModel
                .builder()
                .id(agendamentoTransferenciaModel.getId() == 0 ? 0 : agendamentoTransferenciaModel.getId())
                .contaOrigem(agendamentoTransferenciaModel.getContaOrigem())
                .contaDestino(agendamentoTransferenciaModel.getContaDestino())
                .valorTransferencia(agendamentoTransferenciaModel.getValorTransferencia())
                .valorTransferenciaTaxa((Double.valueOf(formatter.format(CalculosUtil.rangeDiasRegraC(
                        agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia(), agendamentoTransferenciaModel.getValorTransferencia())
                ))))
                .valorTransferenciaTotal((Double.valueOf(formatter.format(agendamentoTransferenciaModel.getValorTransferencia() + CalculosUtil.rangeDiasRegraC(
                        agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia(), agendamentoTransferenciaModel.getValorTransferencia())
                ))))
                .dataTransferencia(agendamentoTransferenciaModel.getDataTransferencia())
                .build();
    }


}
