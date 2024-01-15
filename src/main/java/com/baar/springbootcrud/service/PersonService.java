package com.baar.springbootcrud.service;

import com.baar.springbootcrud.dto.PersonDto;
import com.baar.springbootcrud.exception.PersonAlreadtExistsException;
import com.baar.springbootcrud.exception.PersonNotFoundException;
import com.baar.springbootcrud.model.Person;

import java.util.List;

public interface PersonService {
    public Person savePerson(PersonDto personDto) throws PersonAlreadtExistsException;

    public List<PersonDto> getPersons();

    Person findByLastName(String lastName) throws PersonNotFoundException;

    void updatePersonEmail(Integer id, String email) throws PersonNotFoundException;

    void deletePerson(Integer id) throws PersonNotFoundException;

}
