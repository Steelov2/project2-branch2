package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Author.AuthorUpdateDto;
import com.example.bookstore.entities.Author;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;

    }

    @GetMapping("/authorsList")
    public List<AuthorRequestDto> getAll(){
        return authorService.getAll();
    }
    @GetMapping("/author/{authorID}")
    private Optional<AuthorRequestDto> getAuthorById(@PathVariable("authorID") Long id)
    {
        return authorService.getByID(id);
    }
    @GetMapping("/author/genre/{genreName}")
    private List<AuthorResponseDto> getAuthorByGenreName(@PathVariable("genreName") String name)
    {
       return authorService.getAuthorsByGenreName(name);

    }

    @GetMapping("/author/authorName/{name}")
    private List<AuthorRequestDto> getAuthorByName(@PathVariable ("name") String name){
        return authorService.getByName(name);
    }

    @DeleteMapping("/deleteAuthor/{authorID}")
    private void deleteAuthorById(@PathVariable("authorID") Long id)
    {
        authorService.deleteByID(id);
    }
    @PostMapping("/saveAuthor")
    private AuthorResponseDto saveAuthor(@RequestBody AuthorResponseDto authorResponseDto){
        return authorService.create(authorResponseDto);
    }
    @PutMapping("/updateAuthor")
    private void updateAuthor(@RequestBody AuthorUpdateDto authorUpdateDto) throws Throwable {

        authorService.update(authorUpdateDto);
    }





}
