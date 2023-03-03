package com.br.ifsp.journaling;

import com.br.ifsp.journaling.entities.*;
import com.br.ifsp.journaling.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;

@SpringBootApplication
@EnableScheduling
public class JournalingApplication {


    public static void main(String[] args) {

        SpringApplication.run(JournalingApplication.class, args);


    }

}
