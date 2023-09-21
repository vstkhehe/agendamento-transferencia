package com.avaliacao.tokiomarine.service.impl;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import com.avaliacao.tokiomarine.repository.AgendamentoTransferenciaRepository;
import com.avaliacao.tokiomarine.service.AgendamentoTransferenciaService;
import com.avaliacao.tokiomarine.util.RequestBuilderModelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.avaliacao.tokiomarine.util.CalculosUtil.rangeDiasRegraB;
import static com.avaliacao.tokiomarine.util.CalculosUtil.rangeDiasRegraC;
import static com.avaliacao.tokiomarine.util.RequestBuilderModelUtil.*;

@Slf4j
@Service
public class AgendamentoTransferenciaServiceImpl implements AgendamentoTransferenciaService {

    private static final String REGRAS_TRANSFERENCIA_ERRO = "Erro ao salvar agendamento para transferência: Transferência inadequada às regras.";

    @Autowired
    private AgendamentoTransferenciaRepository agendamentoTransferenciaRepository;

    @Override
    public ResponseEntity addAgendamento(AgendamentoTransferenciaModel agendamentoTransferenciaModel) {
        var responseBody = regrasAgendamentoTransferencia(agendamentoTransferenciaModel);
        if(!Objects.isNull(responseBody))
            return ResponseEntity.ok(responseBody);
        else
            return ResponseEntity.badRequest().body(REGRAS_TRANSFERENCIA_ERRO);
    }

    private AgendamentoTransferenciaModel regrasAgendamentoTransferencia(AgendamentoTransferenciaModel agendamentoTransferenciaModel) {
        if (agendamentoTransferenciaModel.getDataTransferencia().equals(agendamentoTransferenciaModel.getDataAgendamento())) {
            return agendamentoTransferenciaRepository.save(buildAgendamentoRegraTipoA(agendamentoTransferenciaModel));
        }
        if (rangeDiasRegraB(agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia())) {
            return agendamentoTransferenciaRepository.save(buildAgendamentoRegraTipoB(agendamentoTransferenciaModel));
        }else{
            rangeDiasRegraC(agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia(), agendamentoTransferenciaModel.getValorTransferencia());
            return agendamentoTransferenciaRepository.save(buildAgendamentoRegraTipoC(agendamentoTransferenciaModel));
        }
        //log.error(REGRAS_TRANSFERENCIA_ERRO + ResponseEntity.badRequest());
        //return null;
    }

    @Override
    public AgendamentoTransferenciaModel getAgendamentoById(long id) {
        return agendamentoTransferenciaRepository.findById(id).get();
    }

    @Override
    public List<AgendamentoTransferenciaModel> getAgendamentos() {
        return (List<AgendamentoTransferenciaModel>) agendamentoTransferenciaRepository.findAll();
    }

    @Override
    public void deleteAgendamento(long id) {
        agendamentoTransferenciaRepository.deleteById(id);
    }
}
