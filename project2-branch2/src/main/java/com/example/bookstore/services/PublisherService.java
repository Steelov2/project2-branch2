package com.example.bookstore.services;

import com.example.bookstore.DTOs.Publisher.PublisherDTO;
import com.example.bookstore.DTOs.Publisher.PublisherGetDto;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
     List<PublisherDTO> getAll();
     Optional<PublisherDTO> getByID(Long id);
     void deleteByID(Long id);
     PublisherGetDto create(PublisherGetDto publisherGetDto);
     void update(PublisherDTO publisherDTO, Long id);
    List<PublisherDTO> getByNameContaining(String name);
}
