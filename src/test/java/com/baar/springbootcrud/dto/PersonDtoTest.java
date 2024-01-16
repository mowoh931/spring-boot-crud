package com.baar.springbootcrud.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonDto.class})
@ExtendWith(SpringExtension.class)
class PersonDtoTest {

    @Test
    void testGetterAndSetter() {
        Integer id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String gender = "Male";
        String ipAddress = "127.0.0.1";

        PersonDto personDto = new PersonDto();
        personDto.setId(id);
        personDto.setFirstName(firstName);
        personDto.setLastName(lastName);
        personDto.setEmail(email);
        personDto.setGender(gender);
        personDto.setIpAddress(ipAddress);

        Assertions.assertEquals(id, personDto.getId());
        Assertions.assertEquals(firstName, personDto.getFirstName());
        Assertions.assertEquals(lastName, personDto.getLastName());
        Assertions.assertEquals(email, personDto.getEmail());
        Assertions.assertEquals(gender, personDto.getGender());
        Assertions.assertEquals(ipAddress, personDto.getIpAddress());
    }

    @Test
    void testAllArgsConstructor() {
        Integer id = 1;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String gender = "Male";
        String ipAddress = "127.0.0.1";

        PersonDto personDto = new PersonDto(id, firstName, lastName, email, gender, ipAddress);

        Assertions.assertEquals(id, personDto.getId());
        Assertions.assertEquals(firstName, personDto.getFirstName());
        Assertions.assertEquals(lastName, personDto.getLastName());
        Assertions.assertEquals(email, personDto.getEmail());
        Assertions.assertEquals(gender, personDto.getGender());
        Assertions.assertEquals(ipAddress, personDto.getIpAddress());
    }

    @Test
    void testNoArgsConstructor() {
        PersonDto personDto = new PersonDto();

        Assertions.assertNull(personDto.getId());
        Assertions.assertNull(personDto.getFirstName());
        Assertions.assertNull(personDto.getLastName());
        Assertions.assertNull(personDto.getEmail());
        Assertions.assertNull(personDto.getGender());
        Assertions.assertNull(personDto.getIpAddress());
    }


    @Test
    void testHashCode() {
        PersonDto personDto = new PersonDto(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        PersonDto personDto1 = new PersonDto(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        PersonDto personDto2 = new PersonDto(2, "Mary", "Doe", "mary.doe@example.com", "Female", "127.0.1.1");
        int result = personDto.hashCode();
        int expectedResult = personDto1.hashCode();
        Assertions.assertEquals(expectedResult, result);
        Assertions.assertNotEquals(personDto2.hashCode(), result);
    }

    @Test
    void testToString() {
        PersonDto personDto = new PersonDto(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        PersonDto personDto1 = new PersonDto(1, "John", "Doe", "john.doe@example.com", "Male", "127.0.0.1");
        PersonDto personDto2 = new PersonDto(2, "Mary", "Doe", "mary.doe@example.com", "Female", "127.0.1.1");
        String result = personDto.toString();
        String expectedResult = personDto1.toString();
        Assertions.assertEquals(expectedResult, result);
        Assertions.assertNotNull(personDto2.toString(), result);
    }
}