package com.br.ifsp.journaling.repository;

import com.br.ifsp.journaling.entities.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TokenUserRepository extends JpaRepository<TokenUser, Long> {

    public Optional<TokenUser> findByEmail(String email);

}
