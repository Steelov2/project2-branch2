package com.example.bookstore.DTOs.Author;


import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Genre.GenreRequestDto;
import com.example.bookstore.entities.Author;
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
    private List<GenreRequestDto> authorsGenresList;

    public Author convertAuthorResponseDtoToEntity() {
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        if (this.getAuthorsBooksList() != null)
            author.setAuthorsBooksList(this.getAuthorsBooksList()
                    .stream()
                    .map(BookResponseDto::convertBookRequestDtoDtoToEntity)
                    .toList());
//        if (this.getAuthorsBooksList() != null)
//            author.setAuthorsGenresList(this.getAuthorsGenresList()
//                    .stream()
//                    .map(GenreRequestDto::convertGenreRequestDtoToEntity)
//                    .toList());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());

        return author;
    }

}
