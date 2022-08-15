package com.example.bookstore.dto.publisher;

import com.example.bookstore.dto.book.BookDto;
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

    private List<BookDto> publishedBooks;





}
