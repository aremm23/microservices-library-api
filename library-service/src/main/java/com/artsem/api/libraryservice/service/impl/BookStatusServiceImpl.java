package com.artsem.api.libraryservice.service.impl;

import com.artsem.api.libraryservice.exception.DataNotFoundedException;
import com.artsem.api.libraryservice.model.BooksStatus;
import com.artsem.api.libraryservice.repository.BookStatusRepository;
import com.artsem.api.libraryservice.service.BookStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookStatusServiceImpl implements BookStatusService {

    private final BookStatusRepository repository;

    @Value("${library.days}")
    private int expireIn;

    @Override
    public BooksStatus setStatusAsEngaged(Long id) {
        BooksStatus booksStatus = findBookStatusById(id);
        if (!booksStatus.getIsFree()) {
            throw new IllegalStateException("Book with id %s is already engaged".formatted(id));
        }
        booksStatus.setReturnDate(LocalDateTime.now().plusDays(expireIn));
        booksStatus.setTakeDate(LocalDateTime.now());
        booksStatus.setIsFree(false);
        return repository.save(booksStatus);
    }

    @Override
    public List<BooksStatus> getAllFreeBooks() {
        return repository.findByIsFree(true);
    }

    private BooksStatus findBookStatusById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundedException("Book with id %s not found".formatted(id))
        );
    }

    @Override
    public BooksStatus setStatusAsFree(Long id) {
        BooksStatus booksStatus = findBookStatusById(id);
        if (booksStatus.getIsFree()) {
            throw new IllegalStateException("Book with id %s is already free".formatted(id));
        }
        booksStatus.setReturnDate(null);
        booksStatus.setTakeDate(null);
        booksStatus.setIsFree(true);
        return repository.save(booksStatus);
    }

    @Override
    public BooksStatus addNewFreeBook(Long id) {
        BooksStatus booksStatus = BooksStatus.builder()
                .id(id)
                .isFree(true)
                .returnDate(null)
                .takeDate(null)
                .build();
        return repository.save(booksStatus);
    }
}
