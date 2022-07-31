package com.example.bookstore.services;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Author.AuthorUpdateDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorRequestDto> getAll();

    Optional<AuthorRequestDto> getByID(Long id);

    void deleteByID(Long id);

    AuthorResponseDto create(AuthorResponseDto authorResponseDto);

    void update(AuthorUpdateDto authorUpdateDto, Long id) throws Throwable;

    List<AuthorRequestDto> getByName(String name);

    List<AuthorResponseDto> getAuthorsByGenreName(String genreName);


}
