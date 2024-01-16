package com.baar.springbootcrud.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class PersonAlreadtExistsExceptionTest {
    @Mock
    Object backtrace;
    @Mock
    Throwable cause;
    @Mock
    List<Throwable> SUPPRESSED_SENTINEL;
    @Mock
    List<Throwable> suppressedExceptions;
    @InjectMocks
    PersonAlreadtExistsException personAlreadtExistsException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Person already exists";
        Throwable cause = new Throwable("Some cause");

        PersonAlreadtExistsException exception = new PersonAlreadtExistsException(message, cause);

        Assertions.assertEquals(message, exception.getMessage());
        Assertions.assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Person already exists";

        PersonAlreadtExistsException exception = new PersonAlreadtExistsException(message);

        Assertions.assertEquals(message, exception.getMessage());
        Assertions.assertNull(exception.getCause());
    }
}





