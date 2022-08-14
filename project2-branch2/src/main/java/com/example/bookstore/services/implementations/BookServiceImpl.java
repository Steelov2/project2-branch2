package com.example.bookstore.services.implementations;

import com.example.bookstore.dto.book.BookCreateDto;
import com.example.bookstore.dto.book.BookRequestDto;
import com.example.bookstore.dto.book.BookUpdateDto;
import com.example.bookstore.entities.*;
import com.example.bookstore.exceptions.ResourceNotFoundException;
import com.example.bookstore.repository.*;
import com.example.bookstore.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    private final PublisherRepo publisherRepo;


    public BookServiceImpl(BookRepo bookRepo, AuthorRepo authorRepo, GenreRepo genreRepo, PublisherRepo publisherRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
        this.publisherRepo = publisherRepo;
    }
    @Override
    public List<BookRequestDto> getAll() {
        return bookRepo.findAll().stream().map(Book::convertBookToBookRequestDto).toList();
    }

    @Override
    public Optional<BookRequestDto> getByID(Long id) {
        if (bookRepo.existsById(id))
            return bookRepo.findById(id).map(Book::convertBookToBookRequestDto);
        else
            throw new ResourceNotFoundException(String.format("The book with ID: %d is not found or doesn't exist", id));
    }

    @Override
    public void deleteByID(Long id) {
        if (bookRepo.existsById(id))
            bookRepo.deleteById(id);
        else throw new ResourceNotFoundException(String.format("The book with ID: %d is not found or already deleted", id));
    }

    @Override
    public BookCreateDto create(BookCreateDto bookCreateDto) {
        Publisher publishers = publisherRepo.findById(bookCreateDto.getPublisherIds()).orElseThrow(()->new ResourceNotFoundException(String.format("No such publisher with ID: %d", bookCreateDto.getPublisherIds())));
        List<Author> authors = authorRepo.findAllByIdIn(bookCreateDto.getAuthorListIds());
        List<Genre> genres = genreRepo.findAllByIdIn(bookCreateDto.getGenreListIds());
        Book book = bookCreateDto.convertAuthorCreateDtoToEntity(genres,authors,publishers);
        return bookRepo.save(book).convertBookToCreateDto();
    }




    @Override
    public void update(BookUpdateDto bookUpdateDto) {
        Book existingBook;
        List<Author> authors = authorRepo.findAllByIdIn(bookUpdateDto.getAuthorListIds());
        List<Genre> genres = genreRepo.findAllByIdIn(bookUpdateDto.getGenreListIds());
        Publisher publishers = publisherRepo.findById(bookUpdateDto.getPublisherIds()).orElseThrow(()->new ResourceNotFoundException(String.format("No such publisher with ID: %d", bookUpdateDto.getPublisherIds())));

        Book book = bookUpdateDto.convertAuthorUpdateDtoToEntity(genres, authors, publishers);

        existingBook = bookRepo.findById(bookUpdateDto.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format("The book with ID %d is not found", bookUpdateDto.getId())));
        existingBook.setName(book.getName());
        existingBook.setNumberOfPages(book.getNumberOfPages());
        existingBook.setPrice(book.getPrice());
        existingBook.setId(book.getId());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setYearOfIssue(book.getYearOfIssue());
        existingBook.setAuthorList(book.getAuthorList());
        existingBook.setBooksGenreList(book.getBooksGenreList());
        existingBook.setIsInStock(book.getIsInStock());
        bookRepo.save(book);


    }

    @Override
    public List<BookRequestDto> getByNameContaining(String name) {
        if (bookRepo.existsByNameIsContainingIgnoreCase(name))
            return bookRepo.findByNameIsContainingIgnoreCase(name)
                    .stream()
                    .map(Book::convertBookToBookRequestDto).toList();
        else
            throw new ResourceNotFoundException(String.format("The book with name: %s is not found or doesnt exist", name));
    }

    @Override
    public Set<BookRequestDto> getByGenreName(List<String> genreName) {
        if (genreRepo.existsByGenreNameIn(genreName))

                return bookRepo.findAllByGenre(genreName)
                        .stream()
                        .map(Book::convertBookToBookRequestDto).collect(Collectors.toSet());


        else
            throw new ResourceNotFoundException(String.format("The genre with name %s doesn't exist", genreName.toString()));
    }




}
