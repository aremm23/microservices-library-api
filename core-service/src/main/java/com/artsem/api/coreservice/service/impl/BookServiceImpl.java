package com.artsem.api.coreservice.service.impl;

import com.artsem.api.coreservice.exception.DataNotCreatedException;
import com.artsem.api.coreservice.exception.DataNotFoundedException;
import com.artsem.api.coreservice.model.Book;
import com.artsem.api.coreservice.repository.BookRepository;
import com.artsem.api.coreservice.service.BookService;
import com.artsem.api.coreservice.service.MessageSenderService;
import com.artsem.api.coreservice.model.BookIdMessage;
import com.artsem.api.coreservice.service.converter.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final JsonConverter jsonConverter;

    private final MessageSenderService messageSenderService;

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return findBookById(id);
    }

    private Book findBookById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Book with id %d not found".formatted(id))
        );
    }

    private boolean isExistByIsbn(String isbn) {
        return repository.existsByIsbn(isbn);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(
                () -> new DataNotFoundedException("Book with isbn %s not found".formatted(isbn))
        );
    }

    @Override
    public Book addBook(Book book) {
        if (isExistByIsbn(book.getIsbn())) {
            throw new DataNotCreatedException("Book with isbn %s already exist.".formatted(book.getIsbn()));
        }
        Book savedBook = repository.save(book);
        sendBookToLibrary(savedBook);
        return savedBook;
    }

    private void sendBookToLibrary(Book book) {
        messageSenderService.send(
                jsonConverter.convertBookToJson(
                        BookIdMessage.builder()
                                .id(book.getId())
                                .build()
                )
        );
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = findBookById(id);
        checkIsbn(updatedBook.getIsbn(), existingBook.getIsbn());
        parseUpdatedToExisted(updatedBook, existingBook);
        return repository.save(existingBook);
    }

    private void parseUpdatedToExisted(Book updatedBook, Book existingBook) {
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setName(updatedBook.getName());
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setGenre(updatedBook.getGenre());
    }

    private void checkIsbn(String updatedBookIsbn, String existingBookIsbn) {
        if (isExistByIsbn(updatedBookIsbn) && !updatedBookIsbn.equals(existingBookIsbn)) {
            throw new DataNotCreatedException("Book with isbn %s already exist.".formatted(updatedBookIsbn));
        }
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = findBookById(id);
        repository.delete(book);
    }
}
