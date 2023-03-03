package com.br.ifsp.journaling.entities.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
public class UserLoginRequest {

    @NotNull
    @NotBlank
    private String login;

    @NotNull
    @NotBlank
    private String senha;

}
