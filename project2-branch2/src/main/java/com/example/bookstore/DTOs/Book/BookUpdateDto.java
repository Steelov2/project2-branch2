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
public class BookUpdateDto {
    private Long id;
    private Integer price;
    private List<AuthorUpdateResponseDto> authorList;
    private PublisherResponseDto publisher;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;
    private List<GenreUpdateResponseDto> genreList;


}