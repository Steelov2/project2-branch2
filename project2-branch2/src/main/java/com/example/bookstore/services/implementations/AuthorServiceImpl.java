package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.author.AuthorRequestDto;
import com.example.bookstore.dto.author.AuthorResponseDto;
import com.example.bookstore.dto.genre.GetByGenreDto;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.repository.AuthorRepo;
import com.example.bookstore.repository.GenreRepo;
import com.example.bookstore.entities.Author;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;
    private final BookService bookService;
    private final GenreService genreService;

    public AuthorServiceImpl(AuthorRepo authorRepo, GenreRepo genreRepo, BookService bookService, GenreService genreService) {
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @Override
    public List<AuthorRequestDto> getAll() {
        return authorRepo.findAll().stream().map(Author::convertAuthorToRequestDto).toList();
    }

    @Override
    public Optional<AuthorRequestDto> getByID(Long id) {
        if (authorRepo.existsById(id))
            return authorRepo.findById(id).map(Author::convertAuthorToRequestDto);
        else throw new ResourceNotFoundException(String.format("The author with ID %d is not found", id));
    }

    @Override
    public List<AuthorRequestDto> getByName(String name) {
        if (authorRepo.existsByNameIsContainingIgnoreCase(name) ||
                authorRepo.existsBySurnameIsContainingIgnoreCase(name) ||
                authorRepo.existsByPatronymicIsContainingIgnoreCase(name))
            return authorRepo
                    .findByNameIsContainingIgnoreCaseOrSurnameIsContainingIgnoreCaseOrPatronymicIsContainingIgnoreCase(name, name, name)
                    .stream()
                    .map(Author::convertAuthorToRequestDto)
                    .toList();
        else throw new ResourceNotFoundException("Author is not found or doesn't exist");
    }

    @Override
    public Set<AuthorResponseDto> getAuthorsByGenreName(String name) {
        if (!genreRepo.existsByGenreName(name))
            throw new ResourceNotFoundException(String.format("The genre %s is not found or doesn't exist",name));
        else if (!authorRepo.findAllByGenre(name).isEmpty()){

            return authorRepo.findAllByGenre(name).stream().map(Author::convertAuthorToResponseDto).collect(Collectors.toSet());
//            Второй способ получить автора по жанру
//            List<Author> authorList = new ArrayList<>();
//            bookService.getAuthorByGenreName1(name)
//                    .forEach(book -> authorList.addAll(book.getAuthorList().stream().toList()));
//            return authorList.stream().map(Author::convertAuthorToResponseDto).collect(Collectors.toSet());
        }
        else throw new ResourceNotFoundException("Nothing is found");
    }

    @Override
    public Set<AuthorResponseDto> getAuthorsByGenresList(String genreName) {
        GetByGenreDto getByGenreDto=new GetByGenreDto();
        //genreName.split()
        Set<Genre> genres = (Set<Genre>) getByGenreDto.getGenreList().stream().map(genreService::getByID);
       // return genreService.getByNameContaining(genreName);


        return null;
    }

    @Override
    public void deleteByID(Long id) {
        if (authorRepo.existsById(id))
            authorRepo.deleteById(id);
        else throw
                new ResourceNotFoundException(String.format("The author with ID %d doesn't exist", id));
    }

    @Override
    public void create(AuthorResponseDto authorResponseDto) {

        authorRepo.save(
                new Author(
                        authorResponseDto.getName(),
                        authorResponseDto.getSurname(),
                        authorResponseDto.getPatronymic(),
                        authorResponseDto.getDateOfBirth()

                        )
        );

    }

    @Override
    public void update(AuthorResponseDto authorResponseDto) throws Throwable {
        Author existingAuthor;
        Author author = authorResponseDto.convertAuthorRequestDtoToEntity();
        existingAuthor = authorRepo.findById(author.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format("The author with ID %d is not found", authorResponseDto.getId())));
        existingAuthor.setName(author.getName());
        existingAuthor.setSurname(author.getSurname());
        existingAuthor.setPatronymic(author.getPatronymic());
        existingAuthor.setDateOfBirth(author.getDateOfBirth());
        existingAuthor.setId(author.getId());

        authorRepo.save(author);
    }


}
