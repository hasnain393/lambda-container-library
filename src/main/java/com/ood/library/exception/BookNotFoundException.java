package com.ood.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        //super();
    	super("book not found in system");
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
