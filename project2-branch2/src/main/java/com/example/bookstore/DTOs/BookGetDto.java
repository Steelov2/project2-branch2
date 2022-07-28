package com.example.bookstore.DTOs;

import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BookGetDto {
    private Long id;
    private Integer price;
    private PublisherGetDto publisher;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;

    public BookGetDto(Integer price,
                      PublisherGetDto publisher,
                      String name,
                      Integer numberOfPages,
                      LocalDate yearOfIssue) {
        this.price = price;
        this.publisher = publisher;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.yearOfIssue = yearOfIssue;
    }

    public Book convertGetDtoToEntity() {
        Book book = new Book();
        book.setName(this.getName());
        book.setPublisher(this.getPublisher().convertPublisherGetDtoToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        book.setNumberOfPages(this.getNumberOfPages());

        return book;
    }

}
