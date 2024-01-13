package com.ood.library.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {
        //super();
     super("book not in stock");
    }

    public BookNotAvailableException(String message) {
        super(message);
    }

    public BookNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
