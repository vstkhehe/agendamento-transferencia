package com.avaliacao.tokiomarine.util;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;

public class RequestModelUtil {

    public static double calculoPorcentagem(Double valorTransferencia){
        var porcentagem = (3.0/100.0) * valorTransferencia;
        return porcentagem;
    }

    public static AgendamentoTransferenciaModel buildAgendamentoRegraTipoA(AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        return AgendamentoTransferenciaModel
                .builder()
                .contaOrigem(agendamentoTransferenciaModel.getContaOrigem())
                .contaDestino(agendamentoTransferenciaModel.getContaDestino())
                .valorTransferencia(agendamentoTransferenciaModel.getValorTransferencia())
                .valorTransferenciaTaxa(3.0 + calculoPorcentagem(agendamentoTransferenciaModel.getValorTransferencia()))
                .valorTransferenciaTotal(agendamentoTransferenciaModel.getValorTransferencia()
                        + 3.0 + calculoPorcentagem(agendamentoTransferenciaModel.getValorTransferencia()))
                .dataTransferencia(agendamentoTransferenciaModel.getDataTransferencia())
                .build();
    }


}
