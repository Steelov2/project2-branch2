package com.example.bookstore.controllers.controller1;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/user/books")
public class BookUserController {
    private final BookService bookService;
    @Autowired
    public BookUserController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/booksList")
    public List<BookRequestDto> getAll(){
        return bookService.getAll();
    }
    @GetMapping("/book/{bookID}")
    public Optional<BookRequestDto> getBookById(@PathVariable("bookID") Long id)
    {
        return bookService.getByID(id);
    }
    @GetMapping("/book/bookName/{bookName}")
    public List<BookRequestDto> getBookByName(@PathVariable("bookName") String name){
        return bookService.getByNameContaining(name);
    }

    @GetMapping("/book/genreName/{genreName}")
    public List<BookResponseDto> getBookByGenreName(@PathVariable("genreName") String name){
        return bookService.getByGenreName(name);
    }

}
