package com.example.bookstore.dto.author;


import com.example.bookstore.dto.book.BookResponseDto;
import com.example.bookstore.dto.genre.GenreRequestDto;
import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.repository.GenreRepo;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AuthorRequestDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<BookResponseDto> authorsBooksList;
    private List<GenreRequestDto> authorsGenreList;




}
