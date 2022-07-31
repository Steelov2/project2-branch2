package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Publisher.PublisherRequestDto;
import com.example.bookstore.DTOs.Publisher.PublisherResponseDto;
import com.example.bookstore.DTOs.Publisher.PublisherUpdateDto;
import com.example.bookstore.Repos.PublisherRepo;
import com.example.bookstore.converters.PublisherConverter;
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
        return publisherRepo.findAll().stream().map(PublisherConverter::convertPublisherToRequestDto).toList();
    }

    @Override
    public Optional<PublisherRequestDto> getByID(Long id) {
        return publisherRepo.findById(id).map(PublisherConverter::convertPublisherToRequestDto);
    }

    @Override
    public void deleteByID(Long id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public PublisherResponseDto create(PublisherResponseDto publisherResponseDto) {
        Publisher publisher= PublisherConverter.convertPublisherResponseDtoToEntity(publisherResponseDto);
        publisher=publisherRepo.save(publisher);
        return PublisherConverter.convertPublisherToResponseDto(publisher);
    }
    @Override
    public void update(PublisherUpdateDto publisherUpdateDto, Long id) {
        Publisher existingPublisher;
        Publisher publisher= PublisherConverter.convertAuthorUpdateDtoToEntity(publisherUpdateDto);

        try {
            existingPublisher = publisherRepo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
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
                .stream().map(PublisherConverter::convertPublisherToRequestDto)
                .toList();
    }
}
