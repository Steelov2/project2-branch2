package com.example.bookstore.DTOs;

import com.example.bookstore.entities.Author;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AuthorGetDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;

    public AuthorGetDto(String surname,
                        String name,
                        String patronymic,
                        LocalDate dateOfBirth) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
    }

    public Author convertAuthorGetDtoToEntity() {
        Author author = new Author();
        author.setName(this.getName());
        author.setId(this.getId());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());

        return author;
    }
}
