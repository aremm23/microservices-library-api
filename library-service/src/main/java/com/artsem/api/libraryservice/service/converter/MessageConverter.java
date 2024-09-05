package com.artsem.api.libraryservice.service.converter;

public interface MessageConverter<T> {
    T convert(String message);
}