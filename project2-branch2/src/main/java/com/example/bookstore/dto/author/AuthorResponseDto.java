package com.example.bookstore.dto.author;

import com.example.bookstore.entities.Author;
import lombok.*;

import java.time.LocalDate;

@Data
public class AuthorResponseDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;



    public Author convertAuthorRequestDtoToEntity() {
        Author author = new Author();
        author.setName(this.getName());
        if (this.getId() != null)
            author.setId(this.getId());
        author.setPatronymic(this.getPatronymic());
        author.setSurname(this.getSurname());
        author.setDateOfBirth(this.getDateOfBirth());

        return author;
    }


}
