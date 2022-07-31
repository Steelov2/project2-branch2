package com.example.bookstore.DTOs.Book;


import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Genre.GenreDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.entities.Book;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class BookRequestDto {
    private Long id;
    private Integer price;
    private List<AuthorResponseDto> authorList;
    private PublisherResponseDto publisher;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;
    private List<GenreDto> genreList;





}
