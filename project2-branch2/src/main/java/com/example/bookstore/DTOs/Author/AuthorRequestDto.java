package com.example.bookstore.DTOs.Author;


import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Genre.GenreDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AuthorRequestDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<BookResponseDto> authorsBooksList;
    private List<GenreDto> authorsGenresList;




}
