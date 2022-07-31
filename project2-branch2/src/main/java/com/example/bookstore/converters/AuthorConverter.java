package com.example.bookstore.converters;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Author.AuthorUpdateDto;
import com.example.bookstore.entities.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {
    public static AuthorRequestDto convertAuthorToRequestDto(Author author){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(author, AuthorRequestDto.class);
    }

    public static Author convertAuthorRequestDtoToEntity(AuthorRequestDto authorRequestDTO){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(authorRequestDTO,Author.class);
    }

    public  static Author convertAuthorResponseDtoToEntity(AuthorResponseDto authorResponseDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(authorResponseDto,Author.class);
    }
    public static AuthorResponseDto convertAuthorToResponseDto(Author author){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(author, AuthorResponseDto.class);
    }

    public static AuthorUpdateDto convertAuthorToUpdateDto(Author author) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(author,AuthorUpdateDto.class);
    }

    public  static Author convertAuthorUpdateDtoToEntity(AuthorUpdateDto authorUpdateDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(authorUpdateDto,Author.class);
    }
}
