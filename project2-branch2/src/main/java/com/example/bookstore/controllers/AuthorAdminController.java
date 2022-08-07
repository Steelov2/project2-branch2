package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorAdminController {
    private final AuthorService authorService;

    @Autowired
    public AuthorAdminController(AuthorService authorService) {
        this.authorService = authorService;

    }
    //@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")

    @GetMapping("/authorsList")
    public List<AuthorRequestDto> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/author/{authorID}")
    public Optional<AuthorRequestDto> getAuthorById(@PathVariable("authorID") Long id) {
        return authorService.getByID(id);
    }

    @GetMapping("/author/genre/{genreName}")
    public List<AuthorResponseDto> getAuthorByGenreName(@PathVariable("genreName") String name) {
        return authorService.getAuthorsByGenreName(name);

    }

    @GetMapping("/author/authorName/{name}")
    public List<AuthorRequestDto> getAuthorByName(@PathVariable("name") String name) {
        return authorService.getByName(name);
    }

    @DeleteMapping("/deleteAuthor/{authorID}")
    public void deleteAuthorById(@PathVariable("authorID") Long id) {
        authorService.deleteByID(id);
    }

    @PostMapping("/saveAuthor")
    public void saveAuthor(@RequestBody AuthorResponseDto authorResponseDto) {
        authorService.create(authorResponseDto);
    }

    @PutMapping("/updateAuthor")
    public void updateAuthor(@RequestBody AuthorResponseDto authorResponseDto) throws Throwable {

        authorService.update(authorResponseDto);
    }
}
