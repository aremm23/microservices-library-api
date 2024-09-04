package com.artsem.api.coreservice.model.dto;

import com.artsem.api.coreservice.model.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDto {
    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private Genre genre;

    @NotBlank(message = "Author is mandatory")
    private String author;
}
