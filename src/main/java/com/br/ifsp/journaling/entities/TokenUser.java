package com.br.ifsp.journaling.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class TokenUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String email;

}
