package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Genre.GenreDTO;
import com.example.bookstore.Repos.GenreRepo;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.services.GenreService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepo genreRepo;
    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepo.findAll().stream().map(Genre::convertGenreToDto).toList();
    }

    @Override
    public Optional<GenreDTO> getByID(Long id) {
        return genreRepo.findById(id).map(Genre::convertGenreToDto);
    }

    @Override
    public void deleteByID(Long id) {
        genreRepo.deleteById(id);
    }

    @Override
    public GenreDTO create(GenreDTO genreDTO) {
        Genre genre=genreDTO.convertGenreDtoToEntity();
        return genreRepo.save(genre).convertGenreToDto();
    }

    @Override
    public void update(GenreDTO genreDTO, Long id)  {
        Genre existingGenre;
        Genre genre=genreDTO.convertGenreDtoToEntity();
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
    public List<GenreDTO> getByNameContaining(String name) {
        return genreRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(Genre::convertGenreToDto)
                .toList();
    }
}
