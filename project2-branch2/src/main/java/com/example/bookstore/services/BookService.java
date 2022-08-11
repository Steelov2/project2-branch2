package com.example.bookstore.services;

import com.example.bookstore.dto.book.BookRequestDto;
import com.example.bookstore.dto.book.BookResponseDto;
import com.example.bookstore.dto.book.BookUpdateDto;
import com.example.bookstore.entities.Book;

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
