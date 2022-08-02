package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Author.AuthorRequestDto;
import com.example.bookstore.DTOs.Author.AuthorResponseDto;
import com.example.bookstore.DTOs.Author.AuthorUpdateDto;
import com.example.bookstore.Repos.AuthorRepo;
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
        return authorRepo.findAll().stream().map(Author::convertAuthorToRequestDto).toList();
    }

    @Override
    public Optional<AuthorRequestDto> getByID(Long id) {
        return authorRepo.findById(id).map(Author::convertAuthorToRequestDto);
    }
    @Override
    public List<AuthorRequestDto> getByName(String name) {
       return authorRepo.findByName(name).stream().map(Author::convertAuthorToRequestDto).toList();   }

    @Override
    public List<AuthorResponseDto> getAuthorsByGenreName(String name) {
        return authorRepo.findAllByGenre(name).stream().map(Author::convertAuthorToResponseDto).toList();
    }
    @Override
    public void deleteByID(Long id) {
         authorRepo.deleteById(id);
    }

    @Override
    public AuthorResponseDto create(AuthorResponseDto authorResponseDto) {
        Author author = authorResponseDto.convertAuthorRequestDtoToEntity();
        Author authorCreated = authorRepo.save(author);
        return authorCreated.convertAuthorToResponseDto();
    }
    @Override
    public void update(AuthorUpdateDto authorUpdateDto) throws Throwable {
            Author existingAuthor;
            Author author=authorUpdateDto.convertAuthorUpdateDtoToEntity();
            existingAuthor = authorRepo.findById(author.getId()).orElseThrow();
            existingAuthor.setName(author.getName());
            existingAuthor.setSurname(author.getSurname());
            existingAuthor.setPatronymic(author.getPatronymic());
            existingAuthor.setDateOfBirth(author.getDateOfBirth());
            existingAuthor.setId(author.getId());
//            existingAuthor.setAuthorsGenresList(author.getAuthorsGenresList());
//            existingAuthor.setAuthorsBooksList(author.getAuthorsBooksList());
            authorRepo.save(author);
    }



}
