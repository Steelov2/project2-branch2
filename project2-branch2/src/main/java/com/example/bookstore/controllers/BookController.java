package com.example.bookstore.controllers;

import com.example.bookstore.dto.book.BookCreateDto;
import com.example.bookstore.dto.book.BookRequestDto;

import com.example.bookstore.dto.book.BookUpdateDto;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    //эндпойнт работает, но не работает со спецификацией
    @GetMapping("/booksList")
    public List<BookRequestDto> getAll() {
        return bookService.getAll();
    }
    //полностью рабочий
    @GetMapping("/book/{bookID}")
    public Optional<BookRequestDto> getBookById(@PathVariable("bookID") Long id) {
        return bookService.getByID(id);
    }
    //эндпойнт работает, но не работает со спецификацией
    @GetMapping("/book/bookName/{bookName}")
    public List<BookRequestDto> getBookByName(@PathVariable("bookName") String name) {
        return bookService.getByNameContaining(name);
    }
    //эндпойнт работает, но не работает со спецификацией, выводит жанры по соответствию
    @GetMapping("/book/genreName/{genreName}")
    public Set<BookRequestDto> getBookByGenreName(@PathVariable("genreName") List<String> name) {
        return bookService.getByGenreName(name);
    }
    //полностью рабочий
    @DeleteMapping("/book/{bookID}")
    public void deleteBookById(@PathVariable("bookID") Long id) {
        bookService.deleteByID(id);
    }
    //эндпойнт работает, но не работает со спецификацией

    //TODO переписать на войд и попробовать респонс ентити
    @PostMapping("/saveBook")
    public void saveBook(@RequestBody BookCreateDto bookCreateDto) {
        bookService.create(bookCreateDto);
    }
    //эндпойнт работает, но не работает со спецификацией

    @PutMapping("/updateBook")
    public void updateBook(@RequestBody BookUpdateDto bookUpdateDto) {

        bookService.update(bookUpdateDto);
    }


}
