package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Author.AuthorUpdateDto;
import com.example.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping
public class AuthorController {
    @Autowired
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;

    }

    @GetMapping("/author")
    public List<AuthorRequestDto> getAll(){
        return authorService.getAll();
    }
    @GetMapping("/author/{authorID}")
    private Optional<AuthorRequestDto> getAuthorById(@PathVariable("authorID") Long id)
    {
        return authorService.getByID(id);
    }
    @GetMapping("/author/genre/{genreName}")
    private List<AuthorResponseDto> getAuthorByGenreName(@PathVariable("genreName") String genreName)
    {
        return authorService.getAuthorsByGenreName(genreName);

    }

    @GetMapping("/author/name/{name}")
    private List<AuthorRequestDto> getAuthorByName(@PathVariable ("name") String name){
        return authorService.getByName(name);
    }

    @DeleteMapping("/author/{authorID}")
    private void deleteAuthorById(@PathVariable("authorID") Long id)
    {
        authorService.deleteByID(id);
    }
    @PostMapping("/author")
    private AuthorResponseDto saveBook(@RequestBody AuthorResponseDto authorResponseDto){
        return authorService.create(authorResponseDto);
    }
    @PutMapping("/author/{authorID}")
    private void updateBook(@RequestBody AuthorUpdateDto authorUpdateDto, @PathVariable("authorID") Long id) throws Throwable {
        if(!Objects.equals(id, authorUpdateDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        authorService.update(authorUpdateDto,id);
    }





}
