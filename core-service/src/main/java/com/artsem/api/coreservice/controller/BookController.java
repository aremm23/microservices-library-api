package com.artsem.api.coreservice.controller;

import com.artsem.api.coreservice.model.Book;
import com.artsem.api.coreservice.model.dto.BookDto;
import com.artsem.api.coreservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final ModelMapper mapper;


    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto bookDto) {
        Book book = bookService.addBook(mapper.map(bookDto, Book.class));
        return new ResponseEntity<>(
                mapper.map(book, BookDto.class),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable Long id,
            @RequestBody @Valid BookDto bookDto
    ) {
        Book updatedBook = bookService.updateBook(id, mapper.map(bookDto, Book.class));
        return new ResponseEntity<>(
                mapper.map(updatedBook, BookDto.class),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(
                mapper.map(bookService.getBookById(id), BookDto.class),
                HttpStatus.OK
        );
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn) {
        return new ResponseEntity<>(
                mapper.map(bookService.getBookByIsbn(isbn), BookDto.class),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(
                bookService.getAllBooks().stream().map(
                        book -> mapper.map(book, BookDto.class)
                ).toList(),
                HttpStatus.OK
        );
    }
}
