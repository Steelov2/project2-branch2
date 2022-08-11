package com.example.bookstore.services;

import com.example.bookstore.dto.Publisher.PublisherRequestDto;
import com.example.bookstore.dto.Publisher.PublisherResponseDto;
import com.example.bookstore.dto.Publisher.PublisherUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
     List<PublisherRequestDto> getAll();
     Optional<PublisherRequestDto> getByID(Long id);
     void deleteByID(Long id);
     PublisherResponseDto create(PublisherResponseDto publisherResponseDto);
     void update(PublisherUpdateDto publisherUpdateDto);
    List<PublisherRequestDto> getByNameContaining(String name);
}
