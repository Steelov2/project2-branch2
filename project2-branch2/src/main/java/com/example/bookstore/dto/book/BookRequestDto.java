package com.example.bookstore.dto.book;


import com.example.bookstore.dto.author.AuthorResponseDto;
import com.example.bookstore.dto.genre.GenreRequestDto;
import com.example.bookstore.dto.publisher.PublisherResponseDto;
import com.example.bookstore.entities.Book;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Data

public class BookRequestDto {
    private Long id;
    private Integer price;
    private PublisherResponseDto publisher;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;


    private List<GenreRequestDto> genreList;
    private List<AuthorResponseDto> authorList;

    public Book convertBookRequestDtoToEntity() {
        Book book = new Book();
        book.setName(this.getName());
        book.setPublisher(this.getPublisher().convertPublisherRequestDtoToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        if (this.getGenreList() != null)
            book.setBooksGenreList(this.getGenreList().stream().map(GenreRequestDto::convertGenreRequestDtoToEntity).toList());
        if (this.getAuthorList() != null)
            book.setAuthorList(this.getAuthorList().stream().map(AuthorResponseDto::convertAuthorRequestDtoToEntity).toList());
        book.setNumberOfPages(this.getNumberOfPages());
        return book;
    }





}
