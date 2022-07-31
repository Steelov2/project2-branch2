package com.example.bookstore.converters;

import com.example.bookstore.DTOs.User.UserRequestDto;
import com.example.bookstore.DTOs.User.UserResponseDto;
import com.example.bookstore.entities.User;
import org.modelmapper.ModelMapper;

public class UserConverter {
    public static UserRequestDto convertUserToRequestDto(User user){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(user, UserRequestDto.class);
    }
    public static User convertUserRequestDtoToEntity(UserRequestDto userRequestDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(userRequestDto,User.class);
    }
    public  static User convertUserResponseDtoToEntity(UserResponseDto userResponseDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(userResponseDto,User.class);
    }
    public static UserResponseDto convertUserToResponseDto(User user){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(user, UserResponseDto.class);
    }


}
