package com.avaliacao.tokiomarine.service.impl;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import com.avaliacao.tokiomarine.repository.AgendamentoTransferenciaRepository;
import com.avaliacao.tokiomarine.service.AgendamentoTransferenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.avaliacao.tokiomarine.util.CalculosUtil.*;
import static com.avaliacao.tokiomarine.util.RequestBuilderModelUtil.*;

@Slf4j
@Service
public class AgendamentoTransferenciaServiceImpl implements AgendamentoTransferenciaService {

    private static final String REGRAS_TRANSFERENCIA_ERRO = "Erro ao salvar agendamento para transferência: Transferência inadequada às regras.";

    private static final String ERRO_CAMPO_ID_UPDATE = "Campo id em branco ou com valor 0";

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

    //Método responsável por direcionar as requests para as devidas regras.
    private AgendamentoTransferenciaModel regrasAgendamentoTransferencia(AgendamentoTransferenciaModel agendamentoTransferenciaModel) {

        //Regra de transferência tipo A
        if (agendamentoTransferenciaModel.getDataTransferencia().equals(agendamentoTransferenciaModel.getDataAgendamento())){
            if(agendamentoTransferenciaModel.getValorTransferencia() <= 1000.0){
                return agendamentoTransferenciaRepository.save(buildAgendamentoRegraTipoA(agendamentoTransferenciaModel));
            }else{
                log.info("Valor acima do permitido para a regra tipo A");
                return null;
            }
        }
        //Regra de transferência tipo B
        if (rangeDiasRegraB(agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia())) {
            if(agendamentoTransferenciaModel.getValorTransferencia() >= 1001.0 && agendamentoTransferenciaModel.getValorTransferencia() <= 2000.0) {
                return agendamentoTransferenciaRepository.save(buildAgendamentoRegraTipoB(agendamentoTransferenciaModel));
            }else{
                if (agendamentoTransferenciaModel.getValorTransferencia() <= 1001.0) {
                    log.info("Valor abaixo do permitido para a regra tipo B");
                } else {
                    log.info("Valor acima do permitido para a regra tipo B");
                }
                return null;
            }
        }

        //Regra de transferência tipo C
        if(agendamentoTransferenciaModel.getValorTransferencia() >2000.0){
                rangeDiasRegraC(agendamentoTransferenciaModel.getDataAgendamento(), agendamentoTransferenciaModel.getDataTransferencia(), agendamentoTransferenciaModel.getValorTransferencia());
                return agendamentoTransferenciaRepository.save(buildAgendamentoRegraTipoC(agendamentoTransferenciaModel));
            }else{
                log.info("Valor abaixo do permitido para a regra tipo C");
                return null;
        }
    }

    @Override
    public AgendamentoTransferenciaModel getAgendamentoById(long id) {
        return agendamentoTransferenciaRepository.findById(id).get();
    }

    @Override
    public ResponseEntity update(AgendamentoTransferenciaModel agendamentoTransferenciaModel) {
        if(agendamentoTransferenciaModel.getId() == 0){
            return ResponseEntity.badRequest().body(ERRO_CAMPO_ID_UPDATE);
        }
        var responseBody = regrasAgendamentoTransferencia(agendamentoTransferenciaModel);
        if(!Objects.isNull(responseBody))
            return ResponseEntity.ok(responseBody);
        else
            return ResponseEntity.badRequest().body(REGRAS_TRANSFERENCIA_ERRO);
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
