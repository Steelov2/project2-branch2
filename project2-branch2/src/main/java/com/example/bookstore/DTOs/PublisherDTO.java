package com.example.bookstore.DTOs;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Publisher;
import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PublisherDTO {
    private long id;

    private String name;

    private List<BookGetDto> publishedBooks;


    public  Publisher convertToEntity( ) {
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        publisher.setPublishedBooksList(this.getPublishedBooks()
                .stream()
                .map(BookGetDto::convertGetDtoToEntity)
                .toList());
        return publisher;
    }
}
