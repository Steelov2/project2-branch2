package com.example.bookstore.services;

import com.example.bookstore.DTOs.Genre.GenreDTO;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreDTO> getAll();
    Optional<GenreDTO> getByID(Long id);
    void deleteByID(Long id);
    GenreDTO create(GenreDTO genre);
    void update(GenreDTO genre, Long id);
    List<GenreDTO> getByNameContaining(String name);
}
