package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.author.AuthorRequestDto;
import com.example.bookstore.dto.author.AuthorResponseDto;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Genre;
import com.example.bookstore.repository.AuthorRepo;
import com.example.bookstore.repository.GenreRepo;
import com.example.bookstore.entities.Author;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.repository.UserRepo;
import com.example.bookstore.services.AuthorService;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    public void displayAuthorsGenresLit(Author author){
        for(Book book:author.getAuthorsBooksList()){
            for(Genre genre:book.getBooksGenreList()){
                author.getAuthorsGenresList().add(genre);
            }
        }
    }


    public AuthorServiceImpl(AuthorRepo authorRepo, GenreRepo genreRepo) {
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;

    }
//    private String username=SecurityContextHolder.getContext().getAuthentication().g;
//    private Boolean ifExists;
//    private Boolean  qwerty (){
//        ifExists=userRepo.existsByUsername(username);
//
//        if()
//            return true;
//
//
//    }


    @Override
    public List<AuthorRequestDto> getAll() {
        List<Author> authors = authorRepo.findAll();
        for(Author author:authors){
            displayAuthorsGenresLit(author);
        }
        return authorRepo.findAll().stream().map(Author::convertAuthorToRequestDto).toList();
    }

    @Override
    public Optional<AuthorRequestDto> getByID(Long id) {
        Author author=authorRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format("The author with ID %d is not found", id)));
        displayAuthorsGenresLit(author);
            return Optional.ofNullable(author.convertAuthorToRequestDto());
    }

    @Override
    public List<AuthorRequestDto> getByFullNameName(String surname, String name, String patronymic) {
        List<Author> authors = authorRepo.findByFullName(surname, name, patronymic);
        for(Author author:authors) {
            displayAuthorsGenresLit(author);
        }
        if (authorRepo.existsByNameIsContainingIgnoreCase(name) ||
                authorRepo.existsBySurnameIsContainingIgnoreCase(surname) ||
                authorRepo.existsByPatronymicIsContainingIgnoreCase(patronymic))
            return authorRepo
                    .findByFullName(surname, name, patronymic)
                    .stream()
                    .map(Author::convertAuthorToRequestDto)
                    .toList();
        else throw new ResourceNotFoundException("Author is not found or doesn't exist");
    }


    @Override
    public Set<AuthorResponseDto> getAuthorsByGenreName(List<String> name) {
        if (!genreRepo.existsByGenreNameIn(name))
            throw new ResourceNotFoundException(String.format("The genre %s is not found or doesn't exist", name));

        return authorRepo.findAllByGenre(name).stream().map(Author::convertAuthorToResponseDto).collect(Collectors.toSet());
//            Второй способ получить автора по жанру
//            List<Author> authorList = new ArrayList<>();
//            bookService.getAuthorByGenreName1(name)
//                    .forEach(book -> authorList.addAll(book.getAuthorList().stream().toList()));
//            return authorList.stream().map(Author::convertAuthorToResponseDto).collect(Collectors.toSet());

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
