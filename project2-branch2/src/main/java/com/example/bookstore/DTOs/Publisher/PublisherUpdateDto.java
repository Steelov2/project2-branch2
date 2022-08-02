package com.example.bookstore.DTOs.Publisher;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.entities.Publisher;
import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PublisherUpdateDto {
    private long id;
    private String name;
    private List<Long> publishedBooksIds;
    public Publisher convertPublisherUpdateDtoToEntity(List<Book> bbb) {
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());


        publisher.setPublishedBooksList(bbb);

        return publisher;
    }


}
