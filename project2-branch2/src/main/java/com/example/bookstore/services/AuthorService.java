package com.example.bookstore.services;

import com.example.bookstore.DTOs.Author.AuthorDTO;
import com.example.bookstore.DTOs.Author.AuthorGetDto;

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
