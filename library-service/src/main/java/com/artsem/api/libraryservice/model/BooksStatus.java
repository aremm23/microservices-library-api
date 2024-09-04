package com.artsem.api.libraryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books_status")
public class BooksStatus {
    @Id
    @SequenceGenerator(name = "bookIdSeqGen", sequenceName = "book_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "bookIdSeqGen")
    private Long id;

    private Boolean is_free;

    private LocalDateTime take_date;

    private LocalDateTime return_date;

}
