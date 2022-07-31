package com.example.bookstore.DTOs.Publisher;

import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookUpdateResponseDto;
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

    private List<BookUpdateResponseDto> publishedBooks;



}
