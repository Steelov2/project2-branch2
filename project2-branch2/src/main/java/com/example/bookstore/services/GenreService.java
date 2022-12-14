package com.example.bookstore.services;

import com.example.bookstore.dto.genre.GenreRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreRequestDto> getAll();
    Optional<GenreRequestDto> getByID(Long id);
    void deleteByID(Long id);
    void create(GenreRequestDto genre);
    void update(GenreRequestDto genre);
    List<GenreRequestDto> getByNameContaining(String name);
}
