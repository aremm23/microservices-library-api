package com.artsem.api.coreservice.service;

import com.artsem.api.coreservice.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book getBookByIsbn(String id);

    Book addBook(Book book);

    Book updateBook(Long id, Book updatedBook);

    void deleteBookById(Long id);
}
