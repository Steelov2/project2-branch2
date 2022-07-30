package com.example.bookstore.controllers;

import com.example.bookstore.DTOs.BookDTO;
import com.example.bookstore.DTOs.GenreDTO;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<GenreDTO> getAll(){
        return  genreService.getAll();
    }
    @GetMapping("/genre/{genreID}")
    private Optional<GenreDTO> getBookById(@PathVariable("genreID") Long id)
    {
        return genreService.getByID(id);
    }
    @GetMapping("/genre/name/{genreName}")
    private List<GenreDTO> getGenreByName(@PathVariable("genreName") String name){
        return genreService.getByNameContaining(name);
    }

    @DeleteMapping("/genre/{genreID}")
    private void deleteBookById(@PathVariable("genreID") Long id)
    {
        genreService.deleteByID(id);
    }
    @PostMapping("/genre")
    private GenreDTO saveBook(@RequestBody GenreDTO genreDTO){
       return genreService.create(genreDTO);
    }
    @PutMapping("/genre/{genreID}")
    private void updateBook(@RequestBody GenreDTO genreDTO,@PathVariable("genreID") long id)    {
        if(!Objects.equals(id, genreDTO.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        genreService.update(genreDTO,id);
    }



}
