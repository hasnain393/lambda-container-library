
package com.ood.library.exception;

public class BookAlreadyAssignedException extends RuntimeException {
    public BookAlreadyAssignedException() {
    	 //super();
    	super("Book already assigned to user");
    }

    public BookAlreadyAssignedException(String message) {
        super(message);
    }

    public BookAlreadyAssignedException(String message, Throwable cause) {
        super(message, cause);
    }
}
