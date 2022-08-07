package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/user/authors")
public class AuthorUserController {
    private final AuthorService authorService;

    @Autowired
    public AuthorUserController(AuthorService authorService) {
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
}
