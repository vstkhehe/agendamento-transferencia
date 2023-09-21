package com.avaliacao.tokiomarine.service;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgendamentoTransferenciaService {

    public AgendamentoTransferenciaModel addAgendamento(AgendamentoTransferenciaModel agendamentoTransferenciaModel);

    public AgendamentoTransferenciaModel getAgendamentoById(long id);

    public List<AgendamentoTransferenciaModel> getAgendamentos();

    public void deleteAgendamento(long id);
}
