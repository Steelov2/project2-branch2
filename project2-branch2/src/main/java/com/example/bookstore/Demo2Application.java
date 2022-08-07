package com.example.bookstore;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Demo2Application {


    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
        System.out.println("****************************************");
        System.out.println( new BCryptPasswordEncoder().encode("12345678"));

    }

}
