package com.example.bookstore.services;

import com.example.bookstore.DTOs.AuthorDTO;
import com.example.bookstore.DTOs.AuthorGetDto;
import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Genre;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDTO> getAll();

    Optional<AuthorDTO> getByID(Long id);

    void deleteByID(Long id);

    AuthorGetDto create(AuthorGetDto authorGetDto);

    void update(AuthorDTO authorDTO, Long id) throws Throwable;

    List<AuthorDTO> getByName(String name);

    List<AuthorGetDto> getAuthorsByGenreName(String genreName);


}
