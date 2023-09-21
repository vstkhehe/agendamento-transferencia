package com.avaliacao.tokiomarine.util;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import lombok.extern.slf4j.Slf4j;

import static com.avaliacao.tokiomarine.util.CalculosUtil.regraTipoA;

@Slf4j
public class RequestBuilderModelUtil {
    public static AgendamentoTransferenciaModel buildAgendamentoRegraTipoA(AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        return AgendamentoTransferenciaModel
                .builder()
                .contaOrigem(agendamentoTransferenciaModel.getContaOrigem())
                .contaDestino(agendamentoTransferenciaModel.getContaDestino())
                .valorTransferencia(agendamentoTransferenciaModel.getValorTransferencia())
                .valorTransferenciaTaxa(3.0 + regraTipoA(agendamentoTransferenciaModel.getValorTransferencia()))
                .valorTransferenciaTotal(agendamentoTransferenciaModel.getValorTransferencia()
                        + 3.0 + regraTipoA(agendamentoTransferenciaModel.getValorTransferencia()))
                .dataTransferencia(agendamentoTransferenciaModel.getDataTransferencia())
                .build();
    }

    public static AgendamentoTransferenciaModel buildAgendamentoRegraTipoB(AgendamentoTransferenciaModel agendamentoTransferenciaModel){
       return AgendamentoTransferenciaModel
                .builder()
                .contaOrigem(agendamentoTransferenciaModel.getContaOrigem())
                .contaDestino(agendamentoTransferenciaModel.getContaDestino())
                .valorTransferencia(agendamentoTransferenciaModel.getValorTransferencia())
                .valorTransferenciaTaxa(12.0)
                .valorTransferenciaTotal(agendamentoTransferenciaModel.getValorTransferencia() + 12.0)
                .dataTransferencia(agendamentoTransferenciaModel.getDataTransferencia())
                .build();
    }

    public static AgendamentoTransferenciaModel buildAgendamentoRegraTipoC(AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        return AgendamentoTransferenciaModel
                .builder()
                .contaOrigem(agendamentoTransferenciaModel.getContaOrigem())
                .contaDestino(agendamentoTransferenciaModel.getContaDestino())
                .valorTransferencia(agendamentoTransferenciaModel.getValorTransferencia())
                .valorTransferenciaTaxa(CalculosUtil.rangeDiasRegraC(
                        agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia(), agendamentoTransferenciaModel.getValorTransferencia())
                )
                .valorTransferenciaTotal(agendamentoTransferenciaModel.getValorTransferencia() + CalculosUtil.rangeDiasRegraC(
                        agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia(), agendamentoTransferenciaModel.getValorTransferencia())
                )
                .dataTransferencia(agendamentoTransferenciaModel.getDataTransferencia())
                .build();
    }


}
