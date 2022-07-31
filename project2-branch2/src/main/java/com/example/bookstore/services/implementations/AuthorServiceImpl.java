package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Author.AuthorUpdateDto;
import com.example.bookstore.Repos.AuthorRepo;
import com.example.bookstore.converters.AuthorConverter;
import com.example.bookstore.entities.Author;
import com.example.bookstore.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
        public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    private static Throwable get() {
        return new IllegalArgumentException("Author with id not found");
    }

    @Override
    public List<AuthorRequestDto> getAll() {
        return authorRepo.findAll().stream().map(AuthorConverter::convertAuthorToRequestDto).toList();
    }

    @Override
    public Optional<AuthorRequestDto> getByID(Long id) {
        return authorRepo.findById(id).map(AuthorConverter::convertAuthorToRequestDto);
    }
    @Override
    public List<AuthorRequestDto> getByName(String name) {
       return authorRepo.findByName(name).stream().map(AuthorConverter::convertAuthorToRequestDto).toList();   }

    @Override
    public List<AuthorResponseDto> getAuthorsByGenreName(String name) {
        return authorRepo.findAllByGenre(name).stream().map(AuthorConverter::convertAuthorToResponseDto).toList();
    }
    @Override
    public void deleteByID(Long id) {
         authorRepo.deleteById(id);
    }

    @Override
    public AuthorResponseDto create(AuthorResponseDto authorResponseDto) {
        Author author= AuthorConverter.convertAuthorResponseDtoToEntity(authorResponseDto);
        author=authorRepo.save(author);
        return AuthorConverter.convertAuthorToResponseDto(author);
    }
    @Override
    public void update(AuthorUpdateDto authorUpdateDto, Long id) throws Throwable {
            Author existingAuthor;
            Author author=AuthorConverter.convertAuthorUpdateDtoToEntity(authorUpdateDto);
            existingAuthor = authorRepo.findById(id).orElseThrow();
            existingAuthor.setName(author.getName());
            existingAuthor.setSurname(author.getSurname());
            existingAuthor.setPatronymic(author.getPatronymic());
            existingAuthor.setDateOfBirth(author.getDateOfBirth());
            existingAuthor.setId(author.getId());
            existingAuthor.setAuthorsGenresList(author.getAuthorsGenresList());
            existingAuthor.setAuthorsBooksList(author.getAuthorsBooksList());
            authorRepo.save(author);
    }



}
