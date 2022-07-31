package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.Book.BookRequestDto;
import com.example.bookstore.DTOs.Book.BookResponseDto;
import com.example.bookstore.DTOs.Book.BookUpdateDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.converters.BookConverter;
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
        return bookRepo.findAll().stream().map(BookConverter::convertBookToRequestDto).toList();
    }

    @Override
    public Optional<BookRequestDto> getByID(Long id) {
        return bookRepo.findById(id).map(BookConverter::convertBookToRequestDto);
    }

    @Override
    public void deleteByID(Long id) {
        bookRepo.deleteById(id);
    }

    //TODO исправить на тот у которого есть список авторов
    @Override
    public BookResponseDto create(BookResponseDto bookResponseDto) {
        Book book= BookConverter.convertBookResponseDtoToEntity(bookResponseDto);
        book=bookRepo.save(book);
        return BookConverter.convertBookToResponseDto(book);
    }


    @Override
    public void update(BookUpdateDto bookUpdateDto, Long id) {
        Book existingBook;
        Book book=BookConverter.convertAuthorUpdateDtoToEntity(bookUpdateDto);
        try {
            existingBook = bookRepo.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
            existingBook.setName(book.getName());
            existingBook.setNumberOfPages(book.getNumberOfPages());
            existingBook.setPrice(book.getPrice());
            existingBook.setAuthorList(book.getAuthorList());
            existingBook.setId(book.getId());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setYearOfIssue(book.getYearOfIssue());
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
                .map(BookConverter::convertBookToRequestDto).toList();
    }

    @Override
    public List<BookResponseDto> getByGenreName(String genreName) {
        return bookRepo.findAllByGenre(genreName)
                .stream()
                .map(BookConverter::convertBookToResponseDto).toList();
    }


}
