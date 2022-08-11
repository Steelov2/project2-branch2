package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class LimitedRightsException extends RuntimeException{
    public LimitedRightsException(String message) {
        super(message);
    }
}
