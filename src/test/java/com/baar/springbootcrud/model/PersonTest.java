package com.baar.springbootcrud.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    /**
     * Method under test: {@link Person#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Person person1 = new Person(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        Person person2 = new Person(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        Person person3 = new Person(2, "Jane", "Smith", "jane.smith@example.com", "Female", "192.168.0.1");


        // Act and Assert
        Assertions.assertEquals(person1, person2);
        Assertions.assertNotEquals(person1, person3);
    }


    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Person#equals(Object)}
     *   <li>{@link Person#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Person person1 = new Person(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        Person person2 = new Person(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        // Act and Assert
        assertEquals(person1, person2);
        int expectedHashCodeResult = person1.hashCode();
        assertEquals(expectedHashCodeResult, person2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Person#equals(Object)}
     *   <li>{@link Person#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Person person = new Person(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");

        Person person2 = new Person(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");

        // Act and Assert
        assertEquals(person, person2);
        int expectedHashCodeResult = person.hashCode();
        assertEquals(expectedHashCodeResult, person2.hashCode());
    }

    @Test
    void testAllArgsConstructor() {
        Integer id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String gender = "Male";
        String ipAddress = "127.0.0.1";

        Person person = new Person(id, firstName, lastName, email, gender, ipAddress);

        Assertions.assertEquals(id, person.getId());
        Assertions.assertEquals(firstName, person.getFirstName());
        Assertions.assertEquals(lastName, person.getLastName());
        Assertions.assertEquals(email, person.getEmail());
        Assertions.assertEquals(gender, person.getGender());
        Assertions.assertEquals(ipAddress, person.getIpAddress());
    }

    @Test
    void testNoArgsConstructor() {
        Person person = new Person();

        Assertions.assertNull(person.getId());
        Assertions.assertNull(person.getFirstName());
        Assertions.assertNull(person.getLastName());
        Assertions.assertNull(person.getEmail());
        Assertions.assertNull(person.getGender());
        Assertions.assertNull(person.getIpAddress());
    }

    @Test
    void testToString() {
        Person person = new Person(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");

        String expectedToString = "Person{id=1, firstName='John', lastName='Doe', email='john.doe@example.com', gender='Male', ipAddress='127.0.0.1'}";
        Assertions.assertEquals(expectedToString, person.toString());
    }

}
