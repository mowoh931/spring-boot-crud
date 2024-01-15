package com.baar.springbootcrud.exception;

public class PersonAlreadtExistsException extends Exception {
    public PersonAlreadtExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonAlreadtExistsException(String message ) {
        super(message);
    }
}
