package com.br.ifsp.journaling.repository;

import com.br.ifsp.journaling.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    public Optional<Token> findByTokenSeq(String token);

}
