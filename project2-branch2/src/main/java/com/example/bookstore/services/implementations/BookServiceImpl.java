package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookUpdateDto;
import com.example.bookstore.Repos.AuthorRepo;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.Repos.GenreRepo;
import com.example.bookstore.Repos.PublisherRepo;
import com.example.bookstore.entities.Book;
import com.example.bookstore.services.BookService;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return bookRepo.findById(id).map(Book::convertBookToBookRequestDto);
    }

    @Override
    public void deleteByID(Long id) {
        bookRepo.deleteById(id);
    }

    //TODO исправить на тот у которого есть список авторов
    @Override
    public BookResponseDto create(BookResponseDto bookResponseDto) {
        Book book = bookResponseDto.convertBookRequestDtoDtoToEntity();
        Book bookCreated = bookRepo.save(book);
        return bookCreated.convertBookToResponseDto();
    }


    @Override
    public void update(BookUpdateDto bookUpdateDto) {
        Book existingBook;
        val authors =authorRepo.findAllByIdIn(bookUpdateDto.getAuthorListIds());
        val genres =genreRepo.findAllByIdIn(bookUpdateDto.getGenreListIds());
        val publishers =publisherRepo.findById(bookUpdateDto.getPublisherIds()).orElseThrow();

        Book book=bookUpdateDto.convertAuthorUpdateDtoToEntity(genres, authors, publishers);

            existingBook = bookRepo.findById(bookUpdateDto.getId()).orElseThrow();
            existingBook.setName(book.getName());
            existingBook.setNumberOfPages(book.getNumberOfPages());
            existingBook.setPrice(book.getPrice());
            existingBook.setId(book.getId());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setYearOfIssue(book.getYearOfIssue());
            existingBook.setAuthorList(book.getAuthorList());
            existingBook.setBooksGenreList(book.getBooksGenreList());
            bookRepo.save(book);


    }

    @Override
    public List<BookRequestDto> getByNameContaining(String name) {
        return bookRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(Book::convertBookToBookRequestDto).toList();
    }

    @Override
    public List<BookResponseDto> getByGenreName(String genreName) {
        return bookRepo.findAllByGenre(genreName)
                .stream()
                .map(Book::convertBookToResponseDto).toList();
    }

    @Override
    public List<Book> getAuthorByGenreName1(String genreName) {
                return bookRepo.findAllByGenre(genreName);

    }


}
