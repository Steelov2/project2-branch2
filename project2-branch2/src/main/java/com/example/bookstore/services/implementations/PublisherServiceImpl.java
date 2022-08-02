package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.DTOs.Publisher.PublisherUpdateDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.Repos.PublisherRepo;

import com.example.bookstore.entities.Publisher;
import com.example.bookstore.services.PublisherService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepo publisherRepo;

    private final BookRepo bookRepo;

    public PublisherServiceImpl(PublisherRepo publisherRepo, BookRepo bookRepo) {
        this.publisherRepo = publisherRepo;
        this.bookRepo = bookRepo;
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
        val books =bookRepo.findAllByIdIn(publisherUpdateDto.getPublishedBooksIds());

        Publisher publisher= publisherUpdateDto.convertPublisherUpdateDtoToEntity(books);

            existingPublisher = publisherRepo.findById(publisher.getId()).orElseThrow();
            existingPublisher.setName(publisher.getName());
            existingPublisher.setId(publisher.getId());
            existingPublisher.setPublishedBooksList(publisher.getPublishedBooksList());
            publisherRepo.save(publisher);


    }

    @Override
    public List<PublisherRequestDto> getByNameContaining(String name) {
        return publisherRepo
                .findByNameIsContainingIgnoreCase(name)
                .stream().map(Publisher::convertPublisherToRequestDto)
                .toList();
    }
}
