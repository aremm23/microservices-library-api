package com.artsem.api.coreservice.service.impl;

import com.artsem.api.coreservice.model.Book;
import com.artsem.api.coreservice.repository.BookRepository;
import com.artsem.api.coreservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Book with id %d not found".formatted(id))
        );
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return repository.findByIsbn(isbn).orElseThrow(
                () -> new NoSuchElementException("Book with isbn %s not found".formatted(isbn))
        );
    }

    @Override
    public Book addBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Book with id %s not found".formatted(id))
        );
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

    @Override
    public void deleteBookById(Long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Book with id %s not found".formatted(id))
        );
        repository.delete(book);
    }
}
