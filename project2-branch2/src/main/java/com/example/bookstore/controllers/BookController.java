package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Book.BookDTO;

import com.example.bookstore.DTOs.Book.BookGetDto;
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
    public List<BookDTO> getAll(){
       return bookService.getAll();
    }
    @GetMapping("/book/{bookID}")
    private Optional<BookDTO> getBookById(@PathVariable("bookID") Long id)
    {
        return bookService.getByID(id);
    }
    @GetMapping("/book/bookName/{bookName}")
    private List<BookDTO> getBookByName(@PathVariable("bookName") String name){
       return bookService.getByNameContaining(name);
    }

    @GetMapping("/book/genreName/{genreName}")
    private List<BookGetDto> getBookByGenreName(@PathVariable("genreName") String name){
        return bookService.getByGenreName(name);
    }

    @DeleteMapping("/book/{bookID}")
    private void deleteBookById(@PathVariable("bookID") Long id)
    {
        bookService.deleteByID(id);
    }
    @PostMapping("/book")
    private BookGetDto saveBook(@RequestBody BookGetDto bookGetDto){
        return bookService.create(bookGetDto);
    }
    @PutMapping("/book/{bookID}")
    private void updateBook(@RequestBody BookDTO bookDTO,@PathVariable("bookID") long id)    {
        if(!Objects.equals(id, bookDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        bookService.update(bookDTO, id);
    }



}

//@PathVariable("bookid") long id
//book.getId()