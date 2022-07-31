package com.example.bookstore.DTOs.Author;

import com.example.bookstore.entities.Author;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AuthorResponseDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dateOfBirth;

    public AuthorResponseDto(String surname,
                             String name,
                             String patronymic,
                             LocalDate dateOfBirth) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
    }


}
