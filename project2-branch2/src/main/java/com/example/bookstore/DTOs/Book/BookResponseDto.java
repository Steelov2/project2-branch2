package com.example.bookstore.DTOs.Book;

import com.example.bookstore.DTOs.Author.AuthorUpdateResponseDto;
import com.example.bookstore.DTOs.Genre.GenreUpdateResponseDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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


}
