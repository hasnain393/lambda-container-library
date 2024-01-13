package com.ood.library.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        //super();
    	super("user not found in system");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
