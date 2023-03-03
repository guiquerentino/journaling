package com.br.ifsp.journaling.service;

import com.br.ifsp.journaling.entities.*;
import com.br.ifsp.journaling.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
public class TokenService {

    @Autowired
    private TokenRepository repository;

    @Autowired
    private TokenUserRepository tokenUserRepository;

    public Token criaNovoToken(TokenUser tokenUser) {

        if(validaInformacoesLogin(tokenUser)) {

            Token token = new Token();

            token.setTokenSeq(UUID.randomUUID().toString());
            token.setTempoExpiracao(LocalDateTime.now().plusMinutes(5));

            repository.save(token);

            return token;
        }

        return null;

    }

    @Scheduled(fixedDelay = 300)
    public void expurgaTokensExpirados() {

        List<Token> tokenList = repository.findAll();

        for (Token token : tokenList) {
            if (token.getTempoExpiracao().isBefore(LocalDateTime.now().minusMinutes(5))) {
                repository.delete(token);
            }
        }

    }

    private boolean validaInformacoesLogin(TokenUser user) {
        Optional<TokenUser> DbUser = tokenUserRepository.findByEmail(user.getEmail());

        if (DbUser.isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean validaToken(String token){

        Optional<Token> tokenDB = repository.findByTokenSeq(token);

        if(tokenDB.isEmpty()){
            return false;
        }

        return true;

    }


}
