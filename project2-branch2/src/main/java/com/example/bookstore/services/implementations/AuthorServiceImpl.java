package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Author.AuthorDTO;
import com.example.bookstore.DTOs.Author.AuthorGetDto;
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
    public List<AuthorDTO> getAll() {
        return authorRepo.findAll().stream().map(Author::convertAuthorToDto).toList();
    }

    @Override
    public Optional<AuthorDTO> getByID(Long id) {
        return authorRepo.findById(id).map(Author::convertAuthorToDto);
    }
    @Override
    public List<AuthorDTO> getByName(String name) {
       return authorRepo.findByName(name).stream().map(Author::convertAuthorToDto).toList();   }

    @Override
    public List<AuthorGetDto> getAuthorsByGenreName(String name) {
        return authorRepo.findAllByGenre(name).stream().map(Author::convertAuthorToGetDto).toList();
    }
    @Override
    public void deleteByID(Long id) {
         authorRepo.deleteById(id);
    }

    @Override
    public AuthorGetDto create(AuthorGetDto authorGetDto) {
        Author author=authorGetDto.convertAuthorGetDtoToEntity();
        return authorRepo.save(author).convertAuthorToGetDto();
    }
    @Override
    public void update(AuthorDTO authorDTO,Long id) throws Throwable {
            Author existingAuthor;
            Author author=authorDTO.convertAuthorDtoToEntity();
            existingAuthor = authorRepo.findById(id).orElseThrow();
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
