package com.artsem.api.libraryservice.repository;

import com.artsem.api.libraryservice.model.BooksStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStatusRepository extends JpaRepository<BooksStatus, Long> {
}
