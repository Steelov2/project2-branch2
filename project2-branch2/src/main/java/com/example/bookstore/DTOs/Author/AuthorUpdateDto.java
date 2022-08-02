package com.example.bookstore.DTOs.Author;

import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Genre.GenreRequestDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.entities.Author;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class AuthorUpdateDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;

    public Author convertAuthorUpdateDtoToEntity() {
        com.example.bookstore.entities.Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
//        author.setAuthorsBooksList(this.getAuthorsBooksListIds());
//        author.setAuthorsGenresList(this.getAuthorsGenresListIds().stream().map(GenreRequestDto::convertGenreRequestDtoToEntity).toList());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());
        return author;
    }
}
