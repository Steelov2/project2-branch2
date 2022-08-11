package com.example.bookstore.entities;

import com.example.bookstore.dto.author.AuthorResponseDto;
import com.example.bookstore.dto.author.AuthorRequestDto;
import com.example.bookstore.dto.genre.GenreRequestDto;
import com.example.bookstore.repository.GenreRepo;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "author")
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor

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
    private Set<Genre> authorsGenresList;


    @Query(value = """
            SELECT *
            FROM author a ,
                 author_books ab,
                 book_genre bg,
                 genre g
            WHERE a.id = ab.author_id
              and bg.genre_id= g.id
              and ab.book_id= bg.book_id
                and a.name = :authorName
""",nativeQuery = true)
    Set<Genre> getAuthorsGenresList(String authorName) {
        return this.authorsGenresList;
    }





    public Author() {
    }

    public Author(String surname, String name, String patronymic, LocalDate dateOfBirth) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
    }





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
        authorRequestDto.setAuthorsGenresList(this.getAuthorsGenresList(this.getName())
                .stream().map(Genre::convertGenreRequestToDto).collect(Collectors.toSet()));
        authorRequestDto.setAuthorsBooksList(this.getAuthorsBooksList().stream().map(Book::convertBookToResponseDto).toList());
        authorRequestDto.setDateOfBirth(this.getDateOfBirth());
        return authorRequestDto;
    }











}
