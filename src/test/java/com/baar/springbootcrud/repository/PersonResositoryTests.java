package com.baar.springbootcrud.repository;

import com.baar.springbootcrud.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PersonResository.class})
@ExtendWith(MockitoExtension.class)
class PersonRepositoryTests {

    @Mock
    private PersonResository personRepository;

    @Test
    void testFindByLastName() {
        // Create a test person
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@example.com");
        person.setGender("Male");
        person.setIpAddress("127.0.0.1");

        // Save the person to the repository
        personRepository.save(person);

        // Find the person by last name

        when(personRepository.findByLastName("Doe")).thenReturn(Optional.of(person));

        // Assert that the person is found
        Optional<Person> optionalPerson = personRepository.findByLastName("Doe");
        Assertions.assertTrue(optionalPerson.isPresent());

        // Assert that the found person has the correct last name
        Person foundPerson = optionalPerson.get();
        Assertions.assertEquals("Doe", foundPerson.getLastName());
    }
}