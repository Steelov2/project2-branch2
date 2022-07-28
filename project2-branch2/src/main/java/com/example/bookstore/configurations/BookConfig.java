package com.example.bookstore.configurations;

import com.example.bookstore.Repos.AuthorRepo;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
//    @Bean
//    CommandLineRunner commandLineRunner(AuthorRepo authorRepo){
//        return args -> {
//            Author author=new Author(
//                    "surname1",
//                    "name1",
//                    "patronymic1",
//                    LocalDate.of(1984,8,19),
//                    List.of(new Book())
//            );
//
//            authorRepo.saveAll(List.of(author));
//
//
//        };
//    }
}
