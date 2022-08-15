package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.publisher.PublisherRequestDto;
import com.example.bookstore.dto.publisher.PublisherResponseDto;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.repository.PublisherRepo;

import com.example.bookstore.entities.Publisher;
import com.example.bookstore.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepo publisherRepo;

    @Autowired
    public PublisherServiceImpl(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<PublisherRequestDto> getAll() {
        return publisherRepo.findAll().stream().map(Publisher::convertPublisherToRequestDto).toList();
    }

    @Override
    public Optional<PublisherRequestDto> getByID(Long id) {
        if (publisherRepo.existsById(id))
            return publisherRepo.findById(id).map(Publisher::convertPublisherToRequestDto);
        else
            throw new ResourceNotFoundException(String.format("The publisher with ID: %d is not found or doesn't exist", id));
    }

    @Override
    public void deleteByID(Long id) {

        if (publisherRepo.existsById(id))
            publisherRepo.deleteById(id);
        else throw
                new ResourceNotFoundException(String.format("The publisher with ID: %d is not found or already deleted", id));
    }

    @Override
    public void create(PublisherResponseDto publisherResponseDto) {
        Publisher publisher = publisherResponseDto.convertPublisherRequestDtoToEntity();
        Publisher publisherCreated = publisherRepo.save(publisher);
        publisherCreated.convertPublisherToResponseDto();
    }

    @Override
    public void update(PublisherResponseDto publisherResponseDto) {
        Publisher existingPublisher;

        Publisher publisher = publisherResponseDto.convertPublisherRequestDtoToEntity();

        existingPublisher = publisherRepo.findById(publisher.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format("The publisher with ID: %d is not found or doesn't exist", publisher.getId())));
        existingPublisher.setName(publisher.getName());
        existingPublisher.setId(publisher.getId());
        existingPublisher.setPublishedBooksList(publisher.getPublishedBooksList());
        publisherRepo.save(publisher);


    }

    @Override
    public List<PublisherRequestDto> getByNameContaining(String name) {
        if (publisherRepo.existsByNameIsContainingIgnoreCase(name))
            return publisherRepo
                    .findByNameIsContainingIgnoreCase(name)
                    .stream().map(Publisher::convertPublisherToRequestDto)
                    .toList();
        else throw
                new ResourceNotFoundException(String.format("The publisher with name %s is not found or doesn't exist", name));
    }
}
