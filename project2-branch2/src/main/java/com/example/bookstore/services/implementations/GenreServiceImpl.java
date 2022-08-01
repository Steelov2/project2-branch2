package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Genre.GenreRequestDto;
import com.example.bookstore.Repos.GenreRepo;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.services.GenreService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public List<GenreRequestDto> getAll() {
        return genreRepo.findAll()
                .stream()
                .map(Genre::convertGenreRequestToDto)
                .toList();
    }

    @Override
    public Optional<GenreRequestDto> getByID(Long id) {
        return genreRepo.findById(id)
                .map(Genre::convertGenreRequestToDto);
    }

    @Override
    public void deleteByID(Long id) {
        genreRepo.deleteById(id);
    }

    @Override
    public GenreRequestDto create(GenreRequestDto genreRequestDTO) {
        Genre genre = genreRequestDTO.convertGenreRequestDtoToEntity();
        Genre genreCreated = genreRepo.save(genre);
        return genreCreated.convertGenreRequestToDto();
    }

    @Override
    public void update(GenreRequestDto genreRequestDTO) {
        Genre existingGenre;
        Genre genre = genreRequestDTO.convertGenreRequestDtoToEntity();
        try {
            existingGenre = genreRepo.findById(genre.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingGenre.setName(genre.getName());
            existingGenre.setId(genre.getId());
            genreRepo.save(genre);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GenreRequestDto> getByNameContaining(String name) {
        return genreRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(Genre::convertGenreRequestToDto)
                .toList();
    }
}
