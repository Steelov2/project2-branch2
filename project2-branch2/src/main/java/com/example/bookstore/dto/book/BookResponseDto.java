package com.example.bookstore.dto.book;

import com.example.bookstore.dto.Publisher.PublisherResponseDto;
import com.example.bookstore.entities.Book;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BookResponseDto {
    private Long id;
    private Integer price;
    private PublisherResponseDto publisher;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;

    public Book convertBookRequestDtoDtoToEntity() {
        Book book = new Book();
        book.setName(this.getName());
        book.setPublisher(this.getPublisher().convertPublisherRequestDtoToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        if (this.getId() != null)
            book.setId(this.getId());
        book.setNumberOfPages(this.getNumberOfPages());
        return book;
    }
}
