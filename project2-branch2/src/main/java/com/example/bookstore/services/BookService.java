package com.example.bookstore.services;

import com.example.bookstore.dto.book.BookCreateDto;
import com.example.bookstore.dto.book.BookRequestDto;
import com.example.bookstore.dto.book.BookUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookService {
     List<BookRequestDto> getAll();
     Optional<BookRequestDto> getByID(Long id);
     void deleteByID(Long id);
     void create(BookCreateDto bookCreateDto);
     void update(BookUpdateDto bookUpdateDto);
     List<BookRequestDto> getByNameContaining(String name);
     Set<BookRequestDto> getByGenreName(List<String> genreName);



}
