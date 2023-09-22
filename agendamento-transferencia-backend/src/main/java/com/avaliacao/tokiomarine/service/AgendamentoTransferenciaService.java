package com.avaliacao.tokiomarine.service;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgendamentoTransferenciaService {

    public ResponseEntity addAgendamento(AgendamentoTransferenciaModel agendamentoTransferenciaModel);

    public AgendamentoTransferenciaModel getAgendamentoById(long id);

    public List<AgendamentoTransferenciaModel> getAgendamentos();

    ResponseEntity<String> update(AgendamentoTransferenciaModel agendamentoTransferenciaModel);

    public void deleteAgendamento(long id);


}
