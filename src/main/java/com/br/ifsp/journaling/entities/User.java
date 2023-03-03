package com.br.ifsp.journaling.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;


    private String login;

    private String senha;

}
