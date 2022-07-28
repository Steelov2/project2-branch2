package com.example.bookstore.entities;

import com.example.bookstore.DTOs.AuthorDTO;
import com.example.bookstore.DTOs.AuthorGetDto;
import com.example.bookstore.DTOs.BookDTO;
import com.example.bookstore.DTOs.BookGetDto;
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
    private long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @OneToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> authorsBooksList;


    @OneToMany
    @JoinTable(
            name = "author_genre",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> authorsGenresList;

        public AuthorDTO convertAuthorToDto() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(this.getName());
        authorDTO.setSurname(this.getSurname());
        authorDTO.setId(this.getId());
        authorDTO.setPatronymic(this.getPatronymic());
        authorDTO.setAuthorsGenresList(this.getAuthorsGenresList().stream().map(Genre::convertGenreToDto).toList());
        authorDTO.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(Book::convertBookToBookGetDto).toList());
        authorDTO.setDateOfBirth(this.getDateOfBirth());
        return authorDTO;
    }

    public AuthorGetDto convertAuthorToGetDto() {
        AuthorGetDto authorGetDto = new AuthorGetDto();
        authorGetDto.setName(this.getName());
        authorGetDto.setSurname(this.getSurname());
        authorGetDto.setId(this.getId());
        authorGetDto.setPatronymic(this.getPatronymic());
        authorGetDto.setDateOfBirth(this.getDateOfBirth());
        return authorGetDto;
    }

}
