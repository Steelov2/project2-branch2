package com.example.bookstore.DTOs.Book;

import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Genre.GenreRequestDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.entities.Publisher;
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
    private Long publisherIds;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;


    private List<Long> genreListIds;
    private List<Long> authorListIds;

    public Book convertAuthorUpdateDtoToEntity(List<Genre> ggg, List<Author> aaa, Publisher ppp) {
        Book book=new Book();
        book.setName(this.getName());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setId(this.getId());


        book.setPublisher(ppp);
        book.setAuthorList(aaa);
        book.setBooksGenreList(ggg);

        return book;
    }

}
