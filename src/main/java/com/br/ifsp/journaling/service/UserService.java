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

    public UserLoginResponse criaContaNoBanco(User user) {

        UserLoginResponse response = new UserLoginResponse();

        if (!contaExisteNoBanco(user)) {
            repository.save(user);
            response.setResponse("Usuário criado com sucesso!");
            response.setStatus(ResultadoLogin.CONTA_CRIADA);
            response.setIdUsuario(user.getID());
            return response;
        }

        response.setResponse("Usuário já existe!");
        response.setStatus(ResultadoLogin.USUARIO_EXISTENTE);

        return response;

    }

    public UserLoginResponse validaLogin(User user) {

        UserLoginResponse response = new UserLoginResponse();


        if (validaInformacoesLogin(user)) {
            response.setResponse("Login com sucesso!");
            response.setStatus(ResultadoLogin.SUCESSO_LOGIN);
            response.setIdUsuario(user.getID());
            return response;
        }

        response.setResponse("Informações incorretas");
        response.setStatus(ResultadoLogin.ERRO_LOGIN);

        return response;

    }

    public UserLoginResponse redefineSenha(User user) {
        Optional<User> DbUser = repository.findByLogin(user.getLogin());

        UserLoginResponse response = new UserLoginResponse();

        if (!DbUser.isEmpty()) {

            if(DbUser.get().getSenha().equals(user.getSenha())){

                response.setStatus(ResultadoLogin.SENHA_IGUAL);
                response.setResponse("Senha igual a anterior");

                return response;

            }

            DbUser.get().setSenha(user.getSenha());
            DbUser.get().setLogin(user.getLogin());

            repository.save(DbUser.get());

            response.setStatus(ResultadoLogin.SENHA_ATUALIZADA);
            response.setResponse("Sucesso ao atualizar senha!");

            return response;
        }

        response.setStatus(ResultadoLogin.USUARIO_INEXISTENTE);
        response.setResponse("Erro ao atualizar senha!");

        return response;

    }

    private boolean contaExisteNoBanco(User user) {
        Optional<User> DbUser = repository.findByLogin(user.getLogin());

        if (DbUser.isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean validaInformacoesLogin(User user) {
        Optional<User> DbUser = repository.findByLoginAndSenha(user.getLogin(), user.getSenha());

        if (DbUser.isEmpty()) {
            return false;
        }

        return true;
    }

}
