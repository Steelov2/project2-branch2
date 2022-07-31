package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Book.BookRequestDto;

import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookUpdateDto;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping
public class BookController {
    @Autowired
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/book")
    public List<BookRequestDto> getAll(){
       return bookService.getAll();
    }
    @GetMapping("/book/{bookID}")
    private Optional<BookRequestDto> getBookById(@PathVariable("bookID") Long id)
    {
        return bookService.getByID(id);
    }
    @GetMapping("/book/bookName/{bookName}")
    private List<BookRequestDto> getBookByName(@PathVariable("bookName") String name){
       return bookService.getByNameContaining(name);
    }

    @GetMapping("/book/genreName/{genreName}")
    private List<BookResponseDto> getBookByGenreName(@PathVariable("genreName") String name){
        return bookService.getByGenreName(name);
    }

    @DeleteMapping("/book/{bookID}")
    private void deleteBookById(@PathVariable("bookID") Long id)
    {
        bookService.deleteByID(id);
    }
    @PostMapping("/book")
    private BookResponseDto saveBook(@RequestBody BookResponseDto bookResponseDto){
        return bookService.create(bookResponseDto);
    }
    @PutMapping("/book/{bookID}")
    private void updateBook(@RequestBody BookUpdateDto bookUpdateDto, @PathVariable("bookID") long id)    {
        if(!Objects.equals(id, bookUpdateDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        bookService.update(bookUpdateDto, id);
    }



}

//@PathVariable("bookid") long id
//book.getId()