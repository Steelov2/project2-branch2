package com.example.bookstore.dto.Publisher;

import com.example.bookstore.dto.book.BookResponseDto;
import com.example.bookstore.entities.Publisher;
import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PublisherRequestDto {
    private long id;

    private String name;

    private List<BookResponseDto> publishedBooks;

    public  Publisher convertPublisherResponseDtoToEntity( ) {
        Publisher publisher = new Publisher();
        publisher.setName(this.getName());
        publisher.setId(this.getId());
        publisher.setPublishedBooksList(this.getPublishedBooks()
                .stream()
                .map(BookResponseDto::convertBookRequestDtoDtoToEntity)
                .toList());
        return publisher;
    }



}
