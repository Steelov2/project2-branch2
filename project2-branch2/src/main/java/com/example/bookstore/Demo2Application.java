package com.example.bookstore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication
//@EnableWebMvc
//@EnableJpaRepositories(basePackages="", entityManagerFactoryRef="emf")

public class Demo2Application {


    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
        System.out.println("****************************************");
        System.out.println( new BCryptPasswordEncoder().encode("12345678"));

    }

}
