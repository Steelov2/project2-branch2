package com.example.bookstore.entities;

import com.example.bookstore.dto.book.BookDto;
import com.example.bookstore.dto.book.BookResponseDto;
import com.example.bookstore.dto.book.BookRequestDto;
import com.example.bookstore.dto.book.BookUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
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
    @ManyToMany()
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {MERGE, DETACH, REFRESH}, targetEntity = Genre.class)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> booksGenreList;



    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    private String name;
    private int numberOfPages;
    private LocalDate yearOfIssue;

    public Book(Long id,
                int price,
                List<Author> authorList,
                List<Genre> booksGenreList,
                Publisher publisher,
                String name,
                int numberOfPages,
                LocalDate yearOfIssue) {
        this.id = id;
        this.price = price;
        this.authorList = authorList;
        this.booksGenreList = booksGenreList;
        this.publisher = publisher;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.yearOfIssue = yearOfIssue;
    }

    public BookRequestDto convertBookToBookRequestDto() {
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setName(this.getName());
        bookRequestDto.setAuthorList(this.getAuthorList().stream().map(Author::convertAuthorToResponseDto).toList());
        bookRequestDto.setId(this.getId());
        bookRequestDto.setPrice(this.getPrice());
        if (this.getPublisher() != null)
            bookRequestDto.setPublisher(this.getPublisher().convertPublisherToResponseDto());
        bookRequestDto.setNumberOfPages(this.getNumberOfPages());
        bookRequestDto.setYearOfIssue(this.getYearOfIssue());
        bookRequestDto.setGenreList(this.getBooksGenreList().stream().map(Genre::convertGenreRequestToDto).toList());
        return bookRequestDto;
    }

    public BookResponseDto convertBookToResponseDto() {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setName(this.getName());
        bookResponseDto.setId(this.getId());
        bookResponseDto.setPrice(this.getPrice());
        bookResponseDto.setPublisherId(this.getPublisher().getId());
        bookResponseDto.setNumberOfPages(this.getNumberOfPages());
        bookResponseDto.setYearOfIssue(this.getYearOfIssue());
        return bookResponseDto;
    }
    public BookDto convertBookToDto() {
        BookDto bookDto = new BookDto();
        bookDto.setName(this.getName());
        bookDto.setId(this.getId());
        bookDto.setPrice(this.getPrice());
        bookDto.setNumberOfPages(this.getNumberOfPages());
        bookDto.setYearOfIssue(this.getYearOfIssue());
        bookDto.setAuthorList(this.getAuthorList().stream().map(Author::convertAuthorToResponseDto).toList());
        bookDto.setGenreList(this.getBooksGenreList().stream().map(Genre::convertGenreRequestToDto).toList());
        return bookDto;
    }

    public BookUpdateDto convertBookToBookUpdateDto() {
        BookUpdateDto bookUpdateDto = new BookUpdateDto();
        bookUpdateDto.setName(this.getName());
        bookUpdateDto.setId(this.getId());
        bookUpdateDto.setPrice(this.getPrice());
        if (this.getPublisher() != null)
            bookUpdateDto.setPublisherIds(bookUpdateDto.getId());
        bookUpdateDto.setNumberOfPages(this.getNumberOfPages());
        bookUpdateDto.setYearOfIssue(this.getYearOfIssue());
        return bookUpdateDto;
    }



}
