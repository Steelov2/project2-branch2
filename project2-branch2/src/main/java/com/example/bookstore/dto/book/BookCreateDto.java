package com.example.bookstore.dto.book;

import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.entities.Publisher;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class BookCreateDto {
    private Long id;
    private Integer price;
    private Long publisherIds;
    private String name;
    private Integer numberOfPages;
    private LocalDate yearOfIssue;
    private List<Long> genreListIds;
    private List<Long> authorListIds;
    private Boolean isInStock;


    public Book convertAuthorCreateDtoToEntity(List<Genre> ggg, List<Author> aaa, Publisher ppp) {
        Book book=new Book();
        book.setName(this.getName());
        book.setPrice(this.getPrice());
        book.setYearOfIssue(this.getYearOfIssue());
        book.setNumberOfPages(this.getNumberOfPages());
        book.setId(this.getId());
        book.setIsInStock(true);

        book.setPublisher(ppp);
        book.setAuthorList(aaa);
        book.setBooksGenreList(ggg);
        return book;
    }
}
