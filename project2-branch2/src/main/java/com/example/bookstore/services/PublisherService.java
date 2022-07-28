package com.example.bookstore.services;

import com.example.bookstore.DTOs.PublisherDTO;
import com.example.bookstore.DTOs.PublisherGetDto;
import com.example.bookstore.entities.Publisher;

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
