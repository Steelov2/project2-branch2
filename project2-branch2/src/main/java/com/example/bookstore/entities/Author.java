package com.example.bookstore.entities;

import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> authorsBooksList;


    @ManyToMany
    @JoinTable(
            name = "author_genre",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> authorsGenresList;

    public AuthorResponseDto convertAuthorToResponseDto() {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(this.getName());
        authorResponseDto.setSurname(this.getSurname());
        authorResponseDto.setId(this.getId());
        authorResponseDto.setPatronymic(this.getPatronymic());
        authorResponseDto.setDateOfBirth(this.getDateOfBirth());
        return authorResponseDto;
    }

    public AuthorRequestDto convertAuthorToRequestDto() {
        AuthorRequestDto authorRequestDto = new AuthorRequestDto();
        authorRequestDto.setName(this.getName());
        authorRequestDto.setSurname(this.getSurname());
        authorRequestDto.setId(this.getId());
        authorRequestDto.setPatronymic(this.getPatronymic());
        authorRequestDto.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(Genre::convertGenreRequestToDto).toList());
        authorRequestDto.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(Book::convertBookToResponseDto).toList());
        authorRequestDto.setDateOfBirth(this.getDateOfBirth());
        return authorRequestDto;
    }


    public AuthorUpdateDto convertAuthorToUpdateDto() {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto();
        authorUpdateDto.setName(this.getName());
        authorUpdateDto.setSurname(this.getSurname());
        authorUpdateDto.setId(this.getId());
        authorUpdateDto.setPatronymic(this.getPatronymic());
        authorUpdateDto.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(Genre::convertGenreRequestToDto).toList());
        authorUpdateDto.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(Book::convertBookToResponseDto).toList());
        authorUpdateDto.setDateOfBirth(this.getDateOfBirth());
        return authorUpdateDto;
    }

//    public AuthorIdDto convertAuthorToIdDto() {
//        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto();
//        authorUpdateDto.setName(this.getName());
//        authorUpdateDto.setSurname(this.getSurname());
//        authorUpdateDto.setId(this.getId());
//        authorUpdateDto.setPatronymic(this.getPatronymic());
//        authorUpdateDto.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(Genre::convertGenreToDto).toList());
//        authorUpdateDto.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(Book::convertBookToBookGetDto).toList());
//        authorUpdateDto.setDateOfBirth(this.getDateOfBirth());
//        return authorUpdateDto;
//    }






}
