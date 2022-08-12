package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<?> alreadyRegisteredHandling(AlreadyRegisteredException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(UserIsBlockedException.class)
    public ResponseEntity<?> userIsBlockedExceptionHandling(UserIsBlockedException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(LimitedRightsException.class)
    public ResponseEntity<?> limitedRightsExceptionHandling(LimitedRightsException exception, WebRequest request){
        ErrorDetails errorDetails =
                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<?> limitedUserNotFoundHandling(UsernameNotFoundException exception, WebRequest request){
//        ErrorDetails errorDetails =
//                new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
//    }
}
