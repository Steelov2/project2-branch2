package com.example.bookstore.DTOs.Publisher;

import com.example.bookstore.DTOs.Book.BookResponseDto;
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



}
