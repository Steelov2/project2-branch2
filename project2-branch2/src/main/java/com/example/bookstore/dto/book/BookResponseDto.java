package com.example.bookstore.dto.book;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Publisher;
import lombok.*;

import java.time.LocalDate;

@Data


public class BookResponseDto {
    private Long id;
    private Integer price;
    private Long publisherId;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;

    public Book convertBookResponseDtoToEntity(Publisher ppp) {
        Book book = new Book();
        book.setName(this.getName());
        book.setPublisher(ppp);
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        book.setNumberOfPages(this.getNumberOfPages());
        return book;
    }
}
