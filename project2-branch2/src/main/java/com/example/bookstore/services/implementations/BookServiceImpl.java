package com.example.bookstore.services.implementations;

import com.example.bookstore.DTOs.BookDTO;
import com.example.bookstore.DTOs.BookGetDto;
import com.example.bookstore.Repos.BookRepo;
import com.example.bookstore.entities.Book;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<BookDTO> getAll() {
        return bookRepo.findAll().stream().map(Book::convertBookToDto).toList();
    }

    @Override
    public Optional<BookDTO> getByID(Long id) {
        return bookRepo.findById(id).map(Book::convertBookToDto);
    }

    @Override
    public void deleteByID(Long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public BookGetDto create(BookGetDto bookGetDto) {
        Book book=bookGetDto.convertGetDtoToEntity();
        return bookRepo.save(book).convertBookToBookGetDto();
    }

    @Override
    public void update(BookDTO bookDTO, Long id) {
        Book existingBook;
        Book book=bookDTO.convertBookDtoToEntity();
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
    public List<BookDTO> getByNameContaining(String name) {
        return bookRepo.findByNameIsContainingIgnoreCase(name)
                .stream()
                .map(Book::convertBookToDto).toList();
    }

    @Override
    public List<BookGetDto> getByGenreName(String genreName) {
        return bookRepo.findAllByGenre(genreName)
                .stream()
                .map(Book::convertBookToBookGetDto).toList();
    }


}
