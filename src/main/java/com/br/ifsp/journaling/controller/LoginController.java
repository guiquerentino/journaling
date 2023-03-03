package com.br.ifsp.journaling.controller;

import com.br.ifsp.journaling.entities.*;
import com.br.ifsp.journaling.entities.dto.*;
import com.br.ifsp.journaling.entities.enums.*;
import com.br.ifsp.journaling.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class LoginController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/user")
    public ResponseEntity<Object> criaUsuario(@RequestBody @Valid UserLoginRequest request) {

        User user = new User();

        user.setLogin(request.getLogin());
        user.setSenha(request.getSenha());

        UserLoginResponse response = service.CriaContaNoBanco(user);

        if (response.getStatus() == ResultadoLogin.SUCESSO_LOGIN) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

    }

    @GetMapping(value = "/user")
    public ResponseEntity<Object> recebeRequisicaoLogin(@RequestBody @Valid UserLoginRequest request) {

        User user = new User();

        user.setSenha(request.getSenha());
        user.setLogin(request.getLogin());

        UserLoginResponse response = service.validaLogin(user);

        if (response.getStatus() == ResultadoLogin.ERRO_LOGIN) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping(value = "/user/password")
    public ResponseEntity<Object> redefineSenhaUsuario(@RequestBody @Valid UserLoginRequest request){

        User user = new User();

        user.setSenha(request.getSenha());
        user.setLogin(request.getLogin());

        UserLoginResponse response = service.redefineSenha(user);

        if (response.getStatus() == ResultadoLogin.USUARIO_INEXISTENTE) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
