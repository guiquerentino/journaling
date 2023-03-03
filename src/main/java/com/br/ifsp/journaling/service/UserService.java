package com.br.ifsp.journaling.service;

import com.br.ifsp.journaling.entities.*;
import com.br.ifsp.journaling.entities.dto.*;
import com.br.ifsp.journaling.entities.enums.*;
import com.br.ifsp.journaling.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserLoginResponse CriaContaNoBanco(User user) {

        UserLoginResponse response = new UserLoginResponse();

        if (!contaExisteNoBanco(user)) {
            repository.save(user);
            response.setResponse("Usuário criado com sucesso!");
            response.setStatus(ResultadoLogin.CONTA_CRIADA);
            return response;
        }

        response.setResponse("Usuário já existe!");
        response.setStatus(ResultadoLogin.USUARIO_EXISTENTE);

        return response;

    }

    public UserLoginResponse validaLogin(User user){

        UserLoginResponse response = new UserLoginResponse();


        if (validaInformacoesLogin(user)) {
            response.setResponse("Login com sucesso!");
            response.setStatus(ResultadoLogin.SUCESSO_LOGIN);
            return response;
        }

        response.setResponse("Informações incorretas");
        response.setStatus(ResultadoLogin.ERRO_LOGIN);

        return response;

    }

    private boolean contaExisteNoBanco(User user) {
        Optional<User> DbUser = repository.findByLogin(user.getLogin());

        if (DbUser.isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean validaInformacoesLogin(User user){
        Optional<User> DbUser = repository.findByLoginAndSenha(user.getLogin(), user.getSenha());

        if (DbUser.isEmpty()) {
            return false;
        }

        return true;
    }

}