package com.example.bookstore.converters;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.DTOs.Publisher.PublisherUpdateDto;
import com.example.bookstore.entities.Publisher;
import org.modelmapper.ModelMapper;

public class PublisherConverter {
    public static PublisherRequestDto convertPublisherToRequestDto(Publisher publisher){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(publisher, PublisherRequestDto.class);
    }
    public static Publisher convertPublisherRequestDtoToEntity(PublisherRequestDto publisherRequestDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(publisherRequestDto,Publisher.class);
    }
    public  static Publisher convertPublisherResponseDtoToEntity(PublisherResponseDto publisherResponseDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(publisherResponseDto,Publisher.class);
    }
    public static PublisherResponseDto convertPublisherToResponseDto(Publisher publisher){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(publisher, PublisherResponseDto.class);
    }

    public static PublisherUpdateDto convertAuthorToUpdateDto(Publisher publisher) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(publisher,PublisherUpdateDto.class);
    }

    public  static Publisher convertAuthorUpdateDtoToEntity(PublisherUpdateDto publisherUpdateDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(publisherUpdateDto,Publisher.class);
    }

}
