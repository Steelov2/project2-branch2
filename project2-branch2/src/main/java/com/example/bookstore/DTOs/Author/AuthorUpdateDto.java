package com.example.bookstore.DTOs.Author;

import com.example.bookstore.DTOs.Book.BookUpdateResponseDto;
import com.example.bookstore.DTOs.Genre.GenreUpdateResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorUpdateDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<BookUpdateResponseDto> authorsBooksList;
    private List<GenreUpdateResponseDto> authorsGenresList;
}
