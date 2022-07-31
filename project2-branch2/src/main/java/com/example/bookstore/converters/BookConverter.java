package com.example.bookstore.converters;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookUpdateDto;
import com.example.bookstore.entities.Author;
import com.example.bookstore.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {
    public static BookRequestDto convertBookToRequestDto(Book book){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(book, BookRequestDto.class);
    }
    public static Book convertBookRequestDtoToEntity(BookRequestDto bookRequestDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(bookRequestDto,Book.class);
    }
    public  static Book convertBookResponseDtoToEntity(BookResponseDto bookResponseDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(bookResponseDto,Book.class);
    }
    public static BookResponseDto convertBookToResponseDto(Book book){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(book, BookResponseDto.class);
    }

    public static BookUpdateDto convertAuthorToUpdateDto(Book book) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(book,BookUpdateDto.class);
    }

    public  static Book convertAuthorUpdateDtoToEntity(BookUpdateDto bookUpdateDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(bookUpdateDto,Book.class);
    }
}
