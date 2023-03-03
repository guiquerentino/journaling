package com.br.ifsp.journaling.entities.dto;

import lombok.*;
import org.springframework.stereotype.*;

import javax.validation.constraints.*;

@Data
public class UserLoginRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String senha;

}
