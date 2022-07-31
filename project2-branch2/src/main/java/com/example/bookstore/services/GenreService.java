package com.example.bookstore.services;

import com.example.bookstore.DTOs.Genre.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreDto> getAll();
    Optional<GenreDto> getByID(Long id);
    void deleteByID(Long id);
    GenreDto create(GenreDto genre);
    void update(GenreDto genre, Long id);
    List<GenreDto> getByNameContaining(String name);
}
