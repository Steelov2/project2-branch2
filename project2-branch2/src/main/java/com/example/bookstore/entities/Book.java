package com.example.bookstore.entities;

import com.example.bookstore.DTOs.BookDTO;
import com.example.bookstore.DTOs.BookGetDto;
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


    public BookDTO convertBookToDto() {
        BookDTO bookDto = new BookDTO();
        bookDto.setName(this.getName());
        bookDto.setAuthorList(this.getAuthorList().stream().map(Author::convertAuthorToGetDto).toList());
        bookDto.setId(this.getId());
        bookDto.setPrice(this.getPrice());
        bookDto.setPublisher(this.getPublisher().convertPublisherToGetDto());
        bookDto.setNumberOfPages(this.getNumberOfPages());
        bookDto.setYearOfIssue(this.getYearOfIssue());
        bookDto.setGenreList(this.getBooksGenreList().stream().map(Genre::convertGenreToDto).toList());
        return bookDto;
    }

    public BookGetDto convertBookToBookGetDto() {
        BookGetDto bookGetDto = new BookGetDto();
        bookGetDto.setName(this.getName());
        bookGetDto.setId(this.getId());
        bookGetDto.setPrice(this.getPrice());
        bookGetDto.setPublisher(this.getPublisher().convertPublisherToGetDto());
        bookGetDto.setNumberOfPages(this.getNumberOfPages());
        bookGetDto.setYearOfIssue(this.getYearOfIssue());
        return bookGetDto;
    }
}
