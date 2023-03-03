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
@RequestMapping(value = "/api/v1/user")
public class LoginController {

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> criaUsuario(@RequestBody @Valid UserLoginRequest request, @RequestHeader String token) {

        if (tokenService.validaToken(token)) {


            User user = new User();

            user.setLogin(request.getLogin());
            user.setSenha(request.getSenha());

            UserLoginResponse response = service.criaContaNoBanco(user);

            if (response.getStatus() == ResultadoLogin.CONTA_CRIADA) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping
    public ResponseEntity<Object> recebeRequisicaoLogin(@RequestBody @Valid UserLoginRequest request, @RequestParam String token) {

        if (tokenService.validaToken(token)) {

            User user = new User();

            user.setSenha(request.getSenha());
            user.setLogin(request.getLogin());

            UserLoginResponse response = service.validaLogin(user);

            if (response.getStatus() == ResultadoLogin.ERRO_LOGIN) {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

    @PutMapping(value = "/password")
    public ResponseEntity<Object> redefineSenhaUsuario(@RequestBody @Valid UserLoginRequest request, @RequestParam String token) {


        if (tokenService.validaToken(token)) {

            User user = new User();

            user.setSenha(request.getSenha());
            user.setLogin(request.getLogin());

            UserLoginResponse response = service.redefineSenha(user);

            if (response.getStatus() == ResultadoLogin.USUARIO_INEXISTENTE) {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
