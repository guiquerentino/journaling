package com.br.ifsp.journaling.controller;

import com.br.ifsp.journaling.entities.*;
import com.br.ifsp.journaling.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;


    @GetMapping
    public ResponseEntity<Token> geraNovoToken(@RequestBody TokenUser tokenUser){

       Token response = tokenService.criaNovoToken(tokenUser);

       if (response == null){
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }

       return new ResponseEntity<>(response,HttpStatus.OK);

    }

}
