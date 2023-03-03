package com.br.ifsp.journaling.entities.dto;

import com.br.ifsp.journaling.entities.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
public class LembreteRequest {

    private User user;

    private String anotacao;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataAlteracao;

    private LocalDateTime dataExclusao;


}
