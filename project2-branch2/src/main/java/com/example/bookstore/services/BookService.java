package com.example.bookstore.services;

import com.example.bookstore.DTOs.Book.BookDTO;
import com.example.bookstore.DTOs.Book.BookGetDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
     List<BookDTO> getAll();
     Optional<BookDTO> getByID(Long id);
     void deleteByID(Long id);
     BookGetDto create(BookGetDto bookGetDto);
     void update(BookDTO bookDTO, Long id);
     List<BookDTO> getByNameContaining(String name);
     List<BookGetDto> getByGenreName(String genreName);



}
