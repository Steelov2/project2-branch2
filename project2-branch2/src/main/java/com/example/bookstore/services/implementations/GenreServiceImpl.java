package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Genre.GenreDto;
import com.example.bookstore.Repos.GenreRepo;
import com.example.bookstore.converters.GenreConverter;
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
    public List<GenreDto> getAll() {
        return genreRepo.findAll()
                .stream()
                .map(GenreConverter::convertGenreToDto)
                .toList();
    }

    @Override
    public Optional<GenreDto> getByID(Long id) {
        return genreRepo.findById(id)
                .map(GenreConverter::convertGenreToDto);
    }

    @Override
    public void deleteByID(Long id) {
        genreRepo.deleteById(id);
    }

    @Override
    public GenreDto create(GenreDto genreDTO) {
        Genre genre = GenreConverter.convertGenreDtoToEntity(genreDTO);
        genre=genreRepo.save(genre);
        return GenreConverter.convertGenreToDto(genre);
    }

    @Override
    public void update(GenreDto genreDTO, Long id) {
        Genre existingGenre;
        Genre genre = GenreConverter.convertGenreDtoToEntity(genreDTO);
        try {
            existingGenre = genreRepo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
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
    public List<GenreDto> getByNameContaining(String name) {
        return genreRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(GenreConverter::convertGenreToDto)
                .toList();
    }
}
