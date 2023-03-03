package com.br.ifsp.journaling.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long Id;

    private String tokenSeq;

    private LocalDateTime tempoExpiracao;

}
