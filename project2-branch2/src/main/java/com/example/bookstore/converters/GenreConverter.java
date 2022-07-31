package com.example.bookstore.converters;

import com.example.bookstore.DTOs.Book.BookUpdateDto;
import com.example.bookstore.DTOs.Genre.GenreDto;
import com.example.bookstore.DTOs.Genre.GenreUpdateResponseDto;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import org.modelmapper.ModelMapper;

public class GenreConverter {
    public static GenreDto convertGenreToDto(Genre genre){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(genre, GenreDto.class);
    }
    public static Genre convertGenreDtoToEntity(GenreDto genreDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(genreDto,Genre.class);
    }
}
