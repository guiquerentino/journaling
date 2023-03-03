package com.br.ifsp.journaling.entities.dto;

import com.br.ifsp.journaling.entities.enums.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
public class UserLoginResponse {

    @JsonProperty(value = "response")
    private String response;

    @JsonProperty(value = "status")
    private ResultadoLogin status;

}
