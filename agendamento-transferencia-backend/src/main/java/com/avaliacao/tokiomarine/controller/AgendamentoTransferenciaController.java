package com.avaliacao.tokiomarine.controller;

import com.avaliacao.tokiomarine.model.AgendamentoTransferenciaModel;
import com.avaliacao.tokiomarine.service.AgendamentoTransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/agendamento-transferencia")
public class AgendamentoTransferenciaController {

    @Autowired
    private AgendamentoTransferenciaService agendamentoTransferenciaService;


    @PostMapping("/add")
    public ResponseEntity addAgendamento(@RequestBody AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        try {
            return ResponseEntity
                    .created(new URI("/api/agendamento-transferencia/" + agendamentoTransferenciaModel.getId()))
                    .body(agendamentoTransferenciaService.addAgendamento(agendamentoTransferenciaModel));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAgendamento(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamentoTransferenciaService.getAgendamentoById(id));

    }

    @GetMapping("/agendamentos")
    public ResponseEntity getAgendamentos(){
        return ResponseEntity.ok(agendamentoTransferenciaService.getAgendamentos());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAgendamento(@PathVariable("id") Long id){
        Optional<AgendamentoTransferenciaModel> agendamentoTransferenciaModel = Optional.ofNullable(agendamentoTransferenciaService.getAgendamentoById(id));

        if(agendamentoTransferenciaModel.isPresent()){
            agendamentoTransferenciaService.deleteAgendamento(id);
            return ResponseEntity.ok("Deleted Successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
