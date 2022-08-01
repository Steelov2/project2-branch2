package com.example.bookstore.entities;

import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookUpdateDto;
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
    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
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
        if (this.getPublisher() != null)
            bookResponseDto.setPublisher(this.getPublisher().convertPublisherToResponseDto());
        bookResponseDto.setNumberOfPages(this.getNumberOfPages());
        bookResponseDto.setYearOfIssue(this.getYearOfIssue());
        return bookResponseDto;
    }

    public BookUpdateDto convertBookToBookUpdateDto() {
        BookUpdateDto bookUpdateDto = new BookUpdateDto();
        bookUpdateDto.setName(this.getName());
        bookUpdateDto.setId(this.getId());
        bookUpdateDto.setPrice(this.getPrice());
        if (this.getPublisher() != null)
            bookUpdateDto.setPublisher(this.getPublisher().convertPublisherToResponseDto());
        bookUpdateDto.setNumberOfPages(this.getNumberOfPages());
        bookUpdateDto.setYearOfIssue(this.getYearOfIssue());
        return bookUpdateDto;
    }



}
