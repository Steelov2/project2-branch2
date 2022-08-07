package com.example.bookstore.services;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorRequestDto> getAll();

    Optional<AuthorRequestDto> getByID(Long id);

    void deleteByID(Long id);

    void create(AuthorResponseDto authorResponseDto);

    void update(AuthorResponseDto authorResponseDto) throws Throwable;

    List<AuthorRequestDto> getByName(String name);

    List<AuthorResponseDto> getAuthorsByGenreName(String genreName);


}
