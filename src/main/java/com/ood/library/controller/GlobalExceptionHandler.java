package com.ood.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ood.library.exception.BookAlreadyAssignedException;
import com.ood.library.exception.BookNotAvailableException;
import com.ood.library.exception.BookNotFoundException;
import com.ood.library.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyAssignedException.class)
    public ResponseEntity<String> handleBookAlreadyAssignedException(BookAlreadyAssignedException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
    
    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<String> handleBookNotAvailableException(BookNotAvailableException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
    
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
