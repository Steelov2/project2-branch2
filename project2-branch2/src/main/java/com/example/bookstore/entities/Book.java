package com.example.bookstore.entities;

import com.example.bookstore.dto.book.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private Long id;
    private int price;
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authorList;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
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
    private Boolean isInStock;


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

    public BookCreateDto convertBookToCreateDto() {
        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setName(this.getName());
        bookCreateDto.setId(this.getId());
        bookCreateDto.setPrice(this.getPrice());
        if (this.getPublisher() != null)
            bookCreateDto.setPublisherIds(bookCreateDto.getId());
        bookCreateDto.setIsInStock(true);
        bookCreateDto.setNumberOfPages(this.getNumberOfPages());
        bookCreateDto.setYearOfIssue(this.getYearOfIssue());
        return bookCreateDto;
    }


}
