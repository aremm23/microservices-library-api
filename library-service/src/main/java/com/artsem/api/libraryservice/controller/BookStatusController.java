package com.artsem.api.libraryservice.controller;

import com.artsem.api.libraryservice.model.BooksStatus;
import com.artsem.api.libraryservice.model.dto.EngagedBookStatusDto;
import com.artsem.api.libraryservice.model.dto.FreeBookStatusDto;
import com.artsem.api.libraryservice.service.BookStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/library")
public class BookStatusController {

    private final BookStatusService bookStatusService;

    private final ModelMapper mapper;

    @Operation(summary = "Get all free books", description = "Retrieve a list of all available (free) books in the library.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of free books retrieved successfully")
    })
    @GetMapping("/books/free")
    public ResponseEntity<List<FreeBookStatusDto>> getAllFreeBooks() {
        List<BooksStatus> freeBooks = bookStatusService.getAllFreeBooks();
        List<FreeBookStatusDto> freeBooksDTO = freeBooks.stream()
                .map(book -> mapper.map(book, FreeBookStatusDto.class)).toList();
        return ResponseEntity.ok(freeBooksDTO);
    }

    @Operation(summary = "Take a book", description = "Set the status of a specific book as engaged (taken) by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book status updated to engaged successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/books/{id}/take")
    public ResponseEntity<EngagedBookStatusDto> takeBook(@PathVariable Long id) {
        BooksStatus booksStatus = bookStatusService.setStatusAsEngaged(id);
        EngagedBookStatusDto bookStatusDTO = mapper.map(booksStatus, EngagedBookStatusDto.class);
        return ResponseEntity.ok(bookStatusDTO);
    }

    @Operation(summary = "Return a book", description = "Set the status of a specific book as free (returned) by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book status updated to free successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/books/{id}/return")
    public ResponseEntity<FreeBookStatusDto> returnBook(@PathVariable Long id) {
        BooksStatus booksStatus = bookStatusService.setStatusAsFree(id);
        FreeBookStatusDto bookStatusDTO = mapper.map(booksStatus, FreeBookStatusDto.class);
        return ResponseEntity.ok(bookStatusDTO);
    }

}