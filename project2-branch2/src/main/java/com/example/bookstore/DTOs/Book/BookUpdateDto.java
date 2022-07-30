package com.example.bookstore.DTOs.Book;

import com.example.bookstore.DTOs.Author.AuthorGetDto;
import com.example.bookstore.DTOs.Genre.GenreDTO;
import com.example.bookstore.DTOs.Publisher.PublisherGetDto;
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
public class BookUpdateDto {
    private Long id;
    private Integer price;
    private List<AuthorGetDto> authorList;
    private PublisherGetDto publisher;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;
    private List<GenreDTO> genreList;

    public Book convertBookUpdateDtoToEntity() {
        Book book = new Book();
        book.setName(this.getName());
        book.setPublisher(this.getPublisher().convertPublisherGetDtoToEntity());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());
        if (this.getGenreList() != null)
            book.setBooksGenreList(this.getGenreList().stream().map(GenreDTO::convertGenreDtoToEntity).toList());
        if (this.getAuthorList() != null)
            book.setAuthorList(this.getAuthorList().stream().map(AuthorGetDto::convertAuthorGetDtoToEntity).toList());
        book.setNumberOfPages(this.getNumberOfPages());

        return book;
    }
}
