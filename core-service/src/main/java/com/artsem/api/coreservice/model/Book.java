package com.artsem.api.coreservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @SequenceGenerator(name = "bookIdSeqGen", sequenceName = "book_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "bookIdSeqGen")
    private Long id;

    @Column(unique = true)
    private String isbn;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String author;

}
