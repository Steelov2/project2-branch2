package com.example.bookstore.services;

import com.example.bookstore.dto.Publisher.PublisherRequestDto;
import com.example.bookstore.dto.Publisher.PublisherResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
     List<PublisherRequestDto> getAll();
     Optional<PublisherRequestDto> getByID(Long id);
     void deleteByID(Long id);
     void create(PublisherResponseDto publisherResponseDto);
     void update(PublisherResponseDto publisherResponseDto);
    List<PublisherRequestDto> getByNameContaining(String name);
}
