package com.baar.springbootcrud.exception;

public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
