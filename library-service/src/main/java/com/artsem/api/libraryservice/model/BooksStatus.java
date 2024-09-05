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
    private Long id;

    private Boolean isFree;

    private LocalDateTime takeDate;

    private LocalDateTime returnDate;

}
