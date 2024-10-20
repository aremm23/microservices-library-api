package com.artsem.api.coreservice.controller;

import com.artsem.api.coreservice.model.Book;
import com.artsem.api.coreservice.model.dto.BookDto;
import com.artsem.api.coreservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new book", description = "This endpoint allows creating a new book. After the book is created, a message is sent through RabbitMQ to the library-service.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto bookDto) {
        Book book = bookService.addBook(mapper.map(bookDto, Book.class));
        return new ResponseEntity<>(
                mapper.map(book, BookDto.class),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Update a book", description = "This endpoint updates an existing book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
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

    @Operation(summary = "Delete a book", description = "This endpoint deletes a book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get a book by ID", description = "This endpoint retrieves a book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(
                mapper.map(bookService.getBookById(id), BookDto.class),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Get a book by ISBN", description = "This endpoint retrieves a book by its ISBN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn) {
        return new ResponseEntity<>(
                mapper.map(bookService.getBookByIsbn(isbn), BookDto.class),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Get all books", description = "This endpoint retrieves a list of all books.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))})
    })
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
