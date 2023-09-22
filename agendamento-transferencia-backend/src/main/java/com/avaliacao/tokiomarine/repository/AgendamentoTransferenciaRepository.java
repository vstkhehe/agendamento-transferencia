package com.avaliacao.tokiomarine.repository;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface AgendamentoTransferenciaRepository extends CrudRepository<AgendamentoTransferenciaModel, Long> {
}
