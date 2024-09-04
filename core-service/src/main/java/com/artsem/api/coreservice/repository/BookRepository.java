package com.artsem.api.coreservice.repository;

import com.artsem.api.coreservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    Boolean existsByIsbn(String isbn);
}
