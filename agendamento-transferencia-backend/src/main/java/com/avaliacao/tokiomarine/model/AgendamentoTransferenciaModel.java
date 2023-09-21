package com.avaliacao.tokiomarine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@Entity
@Table(name = "agendamento_transferencia")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgendamentoTransferenciaModel {

   public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String contaOrigem;
    private String contaDestino;
    private Double valorTransferencia;
    private Double valorTransferenciaTaxa;
    private Double valorTransferenciaTotal;
    private String dataTransferencia;
    @Builder.Default
    private String dataAgendamento = (LocalDate.now().format(dateTimeFormatter));
}
