package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.PublisherDTO;
import com.example.bookstore.DTOs.PublisherGetDto;
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
    public List<PublisherDTO> getAll() {
        return publisherRepo.findAll().stream().map(Publisher::convertPublisherToDto).toList();
    }

    @Override
    public Optional<PublisherDTO> getByID(Long id) {
        return publisherRepo.findById(id).map(Publisher::convertPublisherToDto);
    }

    @Override
    public void deleteByID(Long id) {
        publisherRepo.deleteById(id);
    }

    @Override
    public PublisherGetDto create(PublisherGetDto publisherGetDto) {
        Publisher publisher=publisherGetDto.convertPublisherGetDtoToEntity();
        return publisherRepo.save(publisher).convertPublisherToGetDto();
    }
    @Override
    public void update(PublisherDTO publisherDTO, Long id) {
        Publisher existingPublisher;
        Publisher publisher=publisherDTO.convertPublisherDtoToEntity();

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
    public List<PublisherDTO> getByNameContaining(String name) {
        return publisherRepo
                .findByNameIsContainingIgnoreCase(name)
                .stream().map(Publisher::convertPublisherToDto)
                .toList();
    }
}
