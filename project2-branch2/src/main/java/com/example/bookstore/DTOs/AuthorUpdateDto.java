package com.example.bookstore.DTOs;

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
public class AuthorUpdateDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;
    private List<BookGetDto> authorsBooksList;
    private List<GenreDTO> authorsGenresList;

    public Author convertAuthorUpdateDtoToEntity() {
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        author.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(BookGetDto::convertGetDtoToEntity).toList());
        author.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(GenreDTO::convertGenreDtoToEntity).toList());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());
        return author;
    }
}
