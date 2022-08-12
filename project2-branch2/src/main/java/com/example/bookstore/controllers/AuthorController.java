package com.example.bookstore.controllers;

import com.example.bookstore.dto.author.AuthorRequestDto;
import com.example.bookstore.dto.author.AuthorResponseDto;
import com.example.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;


    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;

    }
//    @PreAuthorize("principal.accountNonLocked")
    @GetMapping("/authorsList")
    public List<AuthorRequestDto> getAll() {
        return authorService.getAll();
    }
//    @PreAuthorize("principal.accountNonLocked")
    @GetMapping("/author/{authorID}")
    public Optional<AuthorRequestDto> getAuthorById(@PathVariable("authorID") Long id) {
        return authorService.getByID(id);
    }
//    @PreAuthorize("principal.accountNonLocked")
    @GetMapping("/author/genreName/{genreName}")
    public Set<AuthorResponseDto> getAuthorByGenreName(@PathVariable("genreName") List<String> name) {
        return authorService.getAuthorsByGenreName(name);

    }
//    @PreAuthorize("principal.accountNonLocked")
    @GetMapping("/author/authorName/{name}")
    public List<AuthorRequestDto> getAuthorByName(@PathVariable("name") String name) {
        return authorService.getByName(name);
    }
//    @PreAuthorize("principal.accountNonLocked")
    @DeleteMapping("/author/{authorID}")
    public void deleteAuthorById(@PathVariable("authorID") Long id) {
        authorService.deleteByID(id);
    }
//    @PreAuthorize("principal.accountNonLocked")
    @PostMapping("/saveAuthor")
    ResponseEntity<Void>saveAuthor(@RequestBody AuthorResponseDto authorResponseDto) {
        authorService.create(authorResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//    @PreAuthorize("principal.accountNonLocked")
    @PutMapping("/updateAuthor")
    public void updateAuthor(@RequestBody AuthorResponseDto authorResponseDto) throws Throwable {

        authorService.update(authorResponseDto);
    }
}
