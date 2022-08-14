package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    Long id;
    String name;
    public ResourceNotFoundException(String message, Long id) {
        super(message);
        this.id=id;
    }

    public ResourceNotFoundException(String message, String name) {
        super(message);
        this.name = name;
    }
}
