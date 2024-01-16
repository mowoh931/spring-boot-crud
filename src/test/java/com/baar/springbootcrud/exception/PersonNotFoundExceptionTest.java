package com.baar.springbootcrud.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonNotFoundExceptionTest {
    /**
     * Method under test:
     * {@link PersonNotFoundException#PersonNotFoundException(String)}
     */
    @Test
    void testNewPersonNotFoundException() {
        // Arrange and Act
        PersonNotFoundException actualPersonNotFoundException = new PersonNotFoundException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualPersonNotFoundException.getLocalizedMessage());
        assertEquals("An error occurred", actualPersonNotFoundException.getMessage());
        assertNull(actualPersonNotFoundException.getCause());
        assertEquals(0, actualPersonNotFoundException.getSuppressed().length);
    }

    /**
     * Method under test:
     * {@link PersonNotFoundException#PersonNotFoundException(String, Throwable)}
     */
    @Test
    void testNewPersonNotFoundException2() {
        // Arrange
        Throwable cause = new Throwable();

        // Act
        PersonNotFoundException actualPersonNotFoundException = new PersonNotFoundException("An error occurred", cause);

        // Assert
        assertEquals("An error occurred", actualPersonNotFoundException.getLocalizedMessage());
        assertEquals("An error occurred", actualPersonNotFoundException.getMessage());
        Throwable cause2 = actualPersonNotFoundException.getCause();
        assertNull(cause2.getLocalizedMessage());
        assertNull(cause2.getMessage());
        assertNull(cause2.getCause());
        Throwable[] suppressed = actualPersonNotFoundException.getSuppressed();
        assertEquals(0, suppressed.length);
        assertSame(cause, cause2);
        assertSame(suppressed, cause2.getSuppressed());
    }
}
