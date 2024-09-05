package com.artsem.api.libraryservice.service.converter;

import com.artsem.api.libraryservice.model.BookIdMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookIdMessageConverter implements MessageConverter<BookIdMessage> {

    private final ObjectMapper objectMapper;

    public BookIdMessage convert(String json) {
        try {
            return objectMapper.readValue(json, BookIdMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to BookIdMessage", e);
        }
    }
}
