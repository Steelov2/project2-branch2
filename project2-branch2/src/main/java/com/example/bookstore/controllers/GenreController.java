package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Genre.GenreRequestDto;
import com.example.bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping
public class GenreController {
    @Autowired
    private final GenreService genreService;


    @Autowired
    public GenreController( GenreService genreService) {
        this.genreService = genreService;

    }
    @GetMapping("/genre")
    public List<GenreRequestDto> getAll(){
        return  genreService.getAll();
    }
    @GetMapping("/genre/{genreID}")
    private Optional<GenreRequestDto> getBookById(@PathVariable("genreID") Long id)
    {
        return genreService.getByID(id);
    }
    @GetMapping("/genre/name/{genreName}")
    private List<GenreRequestDto> getGenreByName(@PathVariable("genreName") String name){
        return genreService.getByNameContaining(name);
    }

    @DeleteMapping("/genre/{genreID}")
    private void deleteBookById(@PathVariable("genreID") Long id)
    {
        genreService.deleteByID(id);
    }
    @PostMapping("/genre")
    private GenreRequestDto saveBook(@RequestBody GenreRequestDto genreRequestDTO){
       return genreService.create(genreRequestDTO);
    }
    @PutMapping("/genre")
    private void updateBook(@RequestBody GenreRequestDto genreRequestDTO)    {

        genreService.update(genreRequestDTO);
    }



}
