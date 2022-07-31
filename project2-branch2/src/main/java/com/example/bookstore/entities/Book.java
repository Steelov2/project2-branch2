package com.example.bookstore.entities;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "BOOK")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName="book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private int price;
    @ManyToMany(mappedBy = "authorsBooksList")
    private List<Author> authorList;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> booksGenreList;



    @ManyToOne
    @JoinColumn(
            name = "publisher_id")
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Publisher publisher;
    private String name;
    private int numberOfPages;
    private LocalDate yearOfIssue;



}
