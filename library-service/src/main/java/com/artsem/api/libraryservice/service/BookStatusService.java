package com.artsem.api.libraryservice.service;

import com.artsem.api.libraryservice.model.BooksStatus;

import java.util.List;

public interface BookStatusService {

    List<BooksStatus> getAllFreeBooks();

    BooksStatus setStatusAsFree(Long id);

    BooksStatus setStatusAsEngaged(Long id);

    BooksStatus addNewFreeBook(Long id);

}
