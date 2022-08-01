package com.example.bookstore.services;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookUpdateDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
     List<BookRequestDto> getAll();
     Optional<BookRequestDto> getByID(Long id);
     void deleteByID(Long id);
     BookResponseDto create(BookResponseDto bookResponseDto);
     void update(BookUpdateDto bookUpdateDto);
     List<BookRequestDto> getByNameContaining(String name);
     List<BookResponseDto> getByGenreName(String genreName);



}
