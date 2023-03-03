package com.br.ifsp.journaling.repository;

import com.br.ifsp.journaling.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByLogin(String email);
    public Optional<User> findByLoginAndSenha(String email, String senha);

}
