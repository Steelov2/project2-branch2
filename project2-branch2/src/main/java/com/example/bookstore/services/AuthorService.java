package com.example.bookstore.services;

import com.example.bookstore.dto.author.AuthorRequestDto;
import com.example.bookstore.dto.author.AuthorResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorService {
    List<AuthorRequestDto> getAll();

    Optional<AuthorRequestDto> getByID(Long id);

    void deleteByID(Long id);

    void create(AuthorResponseDto authorResponseDto);

    void update(AuthorResponseDto authorResponseDto) throws Throwable;

    List<AuthorRequestDto> getByName(String surname, String name, String Patronymic);

    Set<AuthorResponseDto> getAuthorsByGenreName(List<String> genreName);


}
