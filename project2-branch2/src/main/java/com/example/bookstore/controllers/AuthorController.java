package com.example.bookstore.controllers;

import com.example.bookstore.dto.author.AuthorRequestDto;
import com.example.bookstore.dto.author.AuthorResponseDto;
import com.example.bookstore.services.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/authorsList")
    public List<AuthorRequestDto> getAll() {
        return authorService.getAll();
    }
    @GetMapping("/author/{authorID}")
    public Optional<AuthorRequestDto> getAuthorById(@PathVariable("authorID") Long id) {
        return authorService.getByID(id);
    }
    @GetMapping("/author/genreName/{genreName}")
    public Set<AuthorResponseDto> getAuthorByGenreName(@PathVariable("genreName") List<String> name) {
        return authorService.getAuthorsByGenreName(name);

    }
    @GetMapping ("/author/authorName")
    @ResponseBody
    public List<AuthorRequestDto> getAuthorByFullName(@RequestParam(required = false) String surname, @RequestParam(required = false) String name, @RequestParam(required = false) String patronymic) {
        return authorService.getByFullNameName(surname ,name, patronymic);
    }

    @DeleteMapping("/author/{authorID}")
    public void deleteAuthorById(@PathVariable("authorID") Long id) {
        authorService.deleteByID(id);
    }
    @PostMapping("/saveAuthor")
    ResponseEntity<Void>saveAuthor(@RequestBody AuthorResponseDto authorResponseDto) {
        authorService.create(authorResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/updateAuthor")
    public void updateAuthor(@RequestBody AuthorResponseDto authorResponseDto) throws Throwable {

        authorService.update(authorResponseDto);
    }
}
