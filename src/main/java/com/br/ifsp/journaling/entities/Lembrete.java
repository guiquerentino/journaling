package com.br.ifsp.journaling.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Data
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    private User user;

    private String anotacao;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataAlteracao;

    private LocalDateTime dataExclusao;

}
