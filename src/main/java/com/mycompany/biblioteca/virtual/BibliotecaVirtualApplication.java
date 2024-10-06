package com.mycompany.biblioteca.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BibliotecaVirtualApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaVirtualApplication.class, args);
    }
}
