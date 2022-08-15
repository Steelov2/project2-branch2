package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.genre.GenreRequestDto;
import com.example.bookstore.exceptions.LimitedRightsException;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.GenreRepo;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;
    private final BookRepo bookRepo;

@Autowired
    public GenreServiceImpl(GenreRepo genreRepo, BookRepo bookRepo) {
        this.genreRepo = genreRepo;
        this.bookRepo = bookRepo;
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
        if (genreRepo.existsById(id))
            return genreRepo.findById(id)
                    .map(Genre::convertGenreRequestToDto);
        else throw
                new ResourceNotFoundException(String.format("The genre with ID: %d is not found or doesn't exist", id));
    }

    @Override
    public void deleteByID(Long id) {
        genreRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format("The genre with ID: %d is not found or already deleted", id)));

        if((long)bookRepo.findByGenreId(id).size()==0)
              genreRepo.deleteById(id);
        else throw new LimitedRightsException(String.format("You can't delete the genre with ID: %d", id));

    }

    @Override
    public void create(GenreRequestDto genreRequestDTO) {
        Genre genre = genreRequestDTO.convertGenreRequestDtoToEntity();
        Genre genreCreated = genreRepo.save(genre);
        genreCreated.convertGenreRequestToDto();
    }

    @Override
    public void update(GenreRequestDto genreRequestDTO) {
        Genre existingGenre;
        Genre genre = genreRequestDTO.convertGenreRequestDtoToEntity();

        existingGenre = genreRepo.findById(genre.getId())
                .orElseThrow(()->new ResourceNotFoundException(String.format("The genre with ID %d is not found", genreRequestDTO.getId())));
        existingGenre.setGenreName(genre.getGenreName());
        existingGenre.setId(genre.getId());
        genreRepo.save(genre);

    }

    @Override
    public List<GenreRequestDto> getByNameContaining(String name) {
        return genreRepo.findByGenreNameIsContainingIgnoreCase(name)
                .stream()
                .map(Genre::convertGenreRequestToDto)
                .toList();
    }
}
