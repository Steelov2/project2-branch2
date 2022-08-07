package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.Genre.GenreRequestDto;
import com.example.bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/genres")
public class GenreAdminController {
    private final GenreService genreService;


    @Autowired
    public GenreAdminController( GenreService genreService) {
        this.genreService = genreService;

    }
    @GetMapping("/genresList")
    public List<GenreRequestDto> getAll(){
        return  genreService.getAll();
    }
    @GetMapping("/genre/{genreID}")
    public Optional<GenreRequestDto> getBookById(@PathVariable("genreID") Long id)
    {
        return genreService.getByID(id);
    }
    @GetMapping("/genre/genreName/{genreName}")
    public List<GenreRequestDto> getGenreByName(@PathVariable("genreName") String name){
        return genreService.getByNameContaining(name);
    }



    @DeleteMapping("/deleteGenre/{genreID}")
    private void deleteBookById(@PathVariable("genreID") Long id)
    {
        genreService.deleteByID(id);
    }
    @PostMapping("/saveGenre")
    private GenreRequestDto saveBook(@RequestBody GenreRequestDto genreRequestDTO){
        return genreService.create(genreRequestDTO);
    }
    @PutMapping("/updateGenre")
    private void updateBook(@RequestBody GenreRequestDto genreRequestDTO)    {

        genreService.update(genreRequestDTO);
    }
}