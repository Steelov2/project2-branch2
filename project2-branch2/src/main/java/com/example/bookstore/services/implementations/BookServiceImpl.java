package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookUpdateDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.entities.Book;
import com.example.bookstore.services.BookService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;


    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
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
        Book book=bookUpdateDto.convertAuthorUpdateDtoToEntity();
        try {
            existingBook = bookRepo.findById(bookUpdateDto.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingBook.setName(book.getName());
            existingBook.setNumberOfPages(book.getNumberOfPages());
            existingBook.setPrice(book.getPrice());
            existingBook.setId(book.getId());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setYearOfIssue(book.getYearOfIssue());
            existingBook.setAuthorList(book.getAuthorList());
            existingBook.setBooksGenreList(book.getBooksGenreList());
            bookRepo.save(book);
        } catch (ChangeSetPersister.NotFoundException e) {
            e.printStackTrace();
        }

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


}
