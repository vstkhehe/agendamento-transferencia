package com.avaliacao.tokiomarine.service.impl;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import com.avaliacao.tokiomarine.repository.AgendamentoTransferenciaRepository;
import com.avaliacao.tokiomarine.service.AgendamentoTransferenciaService;
import com.avaliacao.tokiomarine.util.RequestModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoTransferenciaServiceImpl implements AgendamentoTransferenciaService {

    @Autowired
    private AgendamentoTransferenciaRepository agendamentoTransferenciaRepository;

    @Override
    public AgendamentoTransferenciaModel addAgendamento(AgendamentoTransferenciaModel agendamentoTransferenciaModel) {
       if(agendamentoTransferenciaModel.getDataTransferencia().equals(agendamentoTransferenciaModel.getDataAgendamento())){
           return agendamentoTransferenciaRepository.save(RequestModelUtil.buildAgendamentoRegraTipoA(agendamentoTransferenciaModel));
       }
       return null;
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
