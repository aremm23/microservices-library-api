package com.artsem.api.coreservice.repository;

import com.artsem.api.coreservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
