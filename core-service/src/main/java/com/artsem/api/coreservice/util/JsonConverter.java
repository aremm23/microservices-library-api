package com.artsem.api.coreservice.util;

import com.artsem.api.coreservice.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JsonConverter {

    private final ObjectMapper objectMapper;

    public String convertBookToJson(BookIdMessage bookId) {
        try {
            return objectMapper.writeValueAsString(bookId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert book to JSON", e);
        }
    }

}
