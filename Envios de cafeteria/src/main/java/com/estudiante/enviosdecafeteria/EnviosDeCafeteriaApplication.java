package com.estudiante.enviosdecafeteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.estudiante.enviosdecafeteria", "com.estudiante.enviosdecafeteria.controller"})
public class EnviosDeCafeteriaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnviosDeCafeteriaApplication.class, args);
    }
}