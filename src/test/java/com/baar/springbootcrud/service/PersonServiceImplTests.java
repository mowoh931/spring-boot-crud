package com.baar.springbootcrud.service;

import com.baar.springbootcrud.dto.PersonDto;
import com.baar.springbootcrud.exception.PersonAlreadtExistsException;
import com.baar.springbootcrud.exception.PersonNotFoundException;
import com.baar.springbootcrud.model.Person;
import com.baar.springbootcrud.repository.PersonResository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PersonServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTests {
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PersonResository personResository;

    @InjectMocks
    private PersonServiceImpl personServiceImpl;


    /**
     * Method under test: {@link PersonServiceImpl#savePerson(PersonDto)}
     */
    @Test
    void testSavePerson() throws PersonAlreadtExistsException {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");
        Optional<Person> ofResult = Optional.of(person);
        when(personResository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(PersonAlreadtExistsException.class, () -> personServiceImpl.savePerson(new PersonDto()));
        verify(personResository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PersonServiceImpl#savePerson(PersonDto)}
     */
    @Test
    void testSavePerson2() throws PersonAlreadtExistsException {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");
        when(personResository.save(Mockito.<Person>any())).thenReturn(person);
        Optional<Person> emptyResult = Optional.empty();
        when(personResository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Person person2 = new Person();
        person2.setEmail("jane.doe@example.org");
        person2.setFirstName("Jane");
        person2.setGender("Gender");
        person2.setId(1);
        person2.setIpAddress("42 Main St");
        person2.setLastName("Doe");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Person>>any())).thenReturn(person2);

        // Act
        Person actualSavePersonResult = personServiceImpl.savePerson(new PersonDto());

        // Assert
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Person>>any());
        verify(personResository).findById(Mockito.<Integer>any());
        verify(personResository).save(Mockito.<Person>any());
        assertSame(person, actualSavePersonResult);
    }

    /**
     * Method under test: {@link PersonServiceImpl#getPersons()}
     */
    @Test
    void testGetPersons() {
        // Arrange
        when(personResository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<PersonDto> actualPersons = personServiceImpl.getPersons();

        // Assert
        verify(personResository).findAll();
        assertTrue(actualPersons.isEmpty());
    }

    /**
     * Method under test: {@link PersonServiceImpl#getPersons()}
     */
    @Test
    void testGetPersons2() {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        when(personResository.findAll()).thenReturn(personList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any())).thenReturn(new PersonDto());

        // Act
        List<PersonDto> actualPersons = personServiceImpl.getPersons();

        // Assert
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any());
        verify(personResository).findAll();
        assertEquals(1, actualPersons.size());
    }

    /**
     * Method under test: {@link PersonServiceImpl#getPersons()}
     */
    @Test
    void testGetPersons3() {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");

        Person person2 = new Person();
        person2.setEmail("john.smith@example.org");
        person2.setFirstName("John");
        person2.setGender("42");
        person2.setId(2);
        person2.setIpAddress("17 High St");
        person2.setLastName("Smith");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person2);
        personList.add(person);
        when(personResository.findAll()).thenReturn(personList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any())).thenReturn(new PersonDto());

        // Act
        List<PersonDto> actualPersons = personServiceImpl.getPersons();

        // Assert
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any());
        verify(personResository).findAll();
        assertEquals(2, actualPersons.size());
    }

    /**
     * Method under test: {@link PersonServiceImpl#findByLastName(String)}
     */
    @Test
    void testFindByLastName() throws PersonNotFoundException {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");
        Optional<Person> ofResult = Optional.of(person);
        when(personResository.findByLastName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Person actualFindByLastNameResult = personServiceImpl.findByLastName("Doe");

        // Assert
        verify(personResository).findByLastName(Mockito.<String>any());
        assertSame(person, actualFindByLastNameResult);
    }

    /**
     * Method under test: {@link PersonServiceImpl#findByLastName(String)}
     */
    @Test
    void testFindByLastName2() throws PersonNotFoundException {
        // Arrange
        Optional<Person> emptyResult = Optional.empty();
        when(personResository.findByLastName(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(PersonNotFoundException.class, () -> personServiceImpl.findByLastName("Doe"));
        verify(personResository).findByLastName(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link PersonServiceImpl#updatePersonEmail(Integer, String)}
     */
    @Test
    void testUpdatePersonEmail() throws PersonNotFoundException {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");
        Optional<Person> ofResult = Optional.of(person);

        Person person2 = new Person();
        person2.setEmail("jane.doe@example.org");
        person2.setFirstName("Jane");
        person2.setGender("Gender");
        person2.setId(1);
        person2.setIpAddress("42 Main St");
        person2.setLastName("Doe");
        when(personResository.save(Mockito.<Person>any())).thenReturn(person2);
        when(personResository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        personServiceImpl.updatePersonEmail(1, "jane.doe@example.org");

        // Assert
        verify(personResository).findById(Mockito.<Integer>any());
        verify(personResository).save(Mockito.<Person>any());
    }

    /**
     * Method under test:
     * {@link PersonServiceImpl#updatePersonEmail(Integer, String)}
     */
    @Test
    void testUpdatePersonEmail2() throws PersonNotFoundException {
        // Arrange
        Optional<Person> emptyResult = Optional.empty();
        when(personResository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(PersonNotFoundException.class, () -> personServiceImpl.updatePersonEmail(1, "jane.doe@example.org"));
        verify(personResository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PersonServiceImpl#deletePerson(Integer)}
     */
    @Test
    void testDeletePerson() throws PersonNotFoundException {
        // Arrange
        Person person = new Person();
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setGender("Gender");
        person.setId(1);
        person.setIpAddress("42 Main St");
        person.setLastName("Doe");
        Optional<Person> ofResult = Optional.of(person);
        doNothing().when(personResository).delete(Mockito.<Person>any());
        when(personResository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        personServiceImpl.deletePerson(1);

        // Assert that nothing has changed
        verify(personResository).delete(Mockito.<Person>any());
        verify(personResository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PersonServiceImpl#deletePerson(Integer)}
     */
    @Test
    void testDeletePerson2() throws PersonNotFoundException {
        // Arrange
        Optional<Person> emptyResult = Optional.empty();
        when(personResository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(PersonNotFoundException.class, () -> personServiceImpl.deletePerson(1));
        verify(personResository).findById(Mockito.<Integer>any());
    }
}
