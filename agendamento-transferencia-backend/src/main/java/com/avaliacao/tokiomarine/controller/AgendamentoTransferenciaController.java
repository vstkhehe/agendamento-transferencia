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

    private static final String URL_ORIGIN = "http://localhost:5173";

    private static final String REGRAS_TRANSFERENCIA_ERRO = "Erro ao salvar agendamento para transferência: Transferência inadequada às regras.";

    @Autowired
    private AgendamentoTransferenciaService agendamentoTransferenciaService;


    @CrossOrigin(origins = URL_ORIGIN)
    @PostMapping("/add")
    public ResponseEntity addAgendamento(@RequestBody AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        try {
            var response = agendamentoTransferenciaService.addAgendamento(agendamentoTransferenciaModel);

            if(response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity
                        .created(new URI("/api/agendamento-transferencia/" + agendamentoTransferenciaModel.getId()))
                        .body(response);
            }else{
                return ResponseEntity.badRequest().body(REGRAS_TRANSFERENCIA_ERRO);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @CrossOrigin(origins = URL_ORIGIN)
    @PutMapping("/edit")
    public ResponseEntity editAgendamentos(@RequestBody AgendamentoTransferenciaModel agendamentoTransferenciaModel){
        return ResponseEntity.ok(agendamentoTransferenciaService.update(agendamentoTransferenciaModel));

    }

    @CrossOrigin(origins = URL_ORIGIN)
    @GetMapping("/{id}")
    public ResponseEntity getAgendamento(@PathVariable("id") Long id){
        return ResponseEntity.ok(agendamentoTransferenciaService.getAgendamentoById(id));

    }

    @CrossOrigin(origins = URL_ORIGIN)
    @GetMapping("/agendamentos")
    public ResponseEntity getAgendamentos(){
        return ResponseEntity.ok(agendamentoTransferenciaService.getAgendamentos());

    }

    @CrossOrigin(origins = URL_ORIGIN)
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
