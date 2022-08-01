package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.DTOs.Publisher.PublisherUpdateDto;
import com.example.bookstore.Repos.PublisherRepo;

import com.example.bookstore.entities.Publisher;
import com.example.bookstore.services.PublisherService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherServiceImpl(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public List<PublisherRequestDto> getAll() {
        return publisherRepo.findAll().stream().map(Publisher::convertPublisherToRequestDto).toList();
    }

    @Override
    public Optional<PublisherRequestDto> getByID(Long id) {
        return publisherRepo.findById(id).map(Publisher::convertPublisherToRequestDto);
    }

    @Override
    public void deleteByID(Long id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public PublisherResponseDto create(PublisherResponseDto publisherResponseDto) {
        Publisher publisher= publisherResponseDto.convertPublisherRequestDtoToEntity();
        Publisher publisherCreated=publisherRepo.save(publisher);
        return publisherCreated.convertPublisherToResponseDto();
    }
    @Override
    public void update(PublisherUpdateDto publisherUpdateDto) {
        Publisher existingPublisher;
        Publisher publisher= publisherUpdateDto.convertPublisherUpdateDtoToEntity();

        try {
            existingPublisher = publisherRepo.findById(publisher.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingPublisher.setName(publisher.getName());
            existingPublisher.setId(publisher.getId());
            existingPublisher.setPublishedBooksList(publisher.getPublishedBooksList());
            publisherRepo.save(publisher);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<PublisherRequestDto> getByNameContaining(String name) {
        return publisherRepo
                .findByNameIsContainingIgnoreCase(name)
                .stream().map(Publisher::convertPublisherToRequestDto)
                .toList();
    }
}
