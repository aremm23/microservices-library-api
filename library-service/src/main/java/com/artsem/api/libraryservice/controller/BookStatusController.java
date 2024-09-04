package com.artsem.api.libraryservice.controller;

import com.artsem.api.libraryservice.model.BooksStatus;
import com.artsem.api.libraryservice.model.dto.EngagedBookStatusDto;
import com.artsem.api.libraryservice.model.dto.FreeBookStatusDto;
import com.artsem.api.libraryservice.service.BookStatusService;
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

    @GetMapping("/books/free")
    public ResponseEntity<List<FreeBookStatusDto>> getAllFreeBooks() {
        List<BooksStatus> freeBooks = bookStatusService.getAllFreeBooks();
        List<FreeBookStatusDto> freeBooksDTO = freeBooks.stream()
                .map(book -> mapper.map(book, FreeBookStatusDto.class)).toList();
        return ResponseEntity.ok(freeBooksDTO);
    }

    @PutMapping("/books/{id}/take")
    public ResponseEntity<EngagedBookStatusDto> takeBook(@PathVariable Long id) {
        BooksStatus booksStatus = bookStatusService.setStatusAsEngaged(id);
        EngagedBookStatusDto bookStatusDTO = mapper.map(booksStatus, EngagedBookStatusDto.class);
        return ResponseEntity.ok(bookStatusDTO);
    }

    @PutMapping("/books/{id}/return")
    public ResponseEntity<FreeBookStatusDto> returnBook(@PathVariable Long id) {
        BooksStatus booksStatus = bookStatusService.setStatusAsFree(id);
        FreeBookStatusDto bookStatusDTO = mapper.map(booksStatus, FreeBookStatusDto.class);
        return ResponseEntity.ok(bookStatusDTO);
    }

}
