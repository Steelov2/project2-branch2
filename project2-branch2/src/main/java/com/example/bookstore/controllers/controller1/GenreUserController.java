package com.example.bookstore.controllers.controller1;

import com.example.bookstore.DTOs.Genre.GenreRequestDto;
import com.example.bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/user/genres")
public class GenreUserController {
    private final GenreService genreService;


    @Autowired
    public GenreUserController( GenreService genreService) {
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

}
