package com.baar.springbootcrud.service;


import com.baar.springbootcrud.dto.PersonDto;
import com.baar.springbootcrud.exception.PersonAlreadtExistsException;
import com.baar.springbootcrud.exception.PersonNotFoundException;
import com.baar.springbootcrud.model.Person;
import com.baar.springbootcrud.repository.PersonResository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "personService")
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final ModelMapper modelMapper;

    private final PersonResository personResository;

    public PersonServiceImpl(PersonResository personResository,
                             ModelMapper modelMapper) {
        this.personResository = personResository;

        this.modelMapper = modelMapper;
    }


    @Override
    public Person savePerson(PersonDto personDto) throws PersonAlreadtExistsException {

        Optional<Person> personFound = personResository.findById(personDto.getId());
        if (personFound.isPresent()) {
            throw new PersonAlreadtExistsException("Person already exists");
        }

        Person person = modelMapper.map(personDto, Person.class);
        return personResository.save(person);
    }

    @Override
    public List<PersonDto> getPersons() {
        return personResository.findAll().stream().map((person) -> modelMapper.map(person, PersonDto.class)).toList();
    }

    @Override
    public Person findByLastName(String lastName) throws PersonNotFoundException {

        return personResository.findByLastName(lastName).orElseThrow(() -> new PersonNotFoundException("No such person"));
    }

    @Override
    public void updatePersonEmail(Integer id, String email) throws PersonNotFoundException {
        Person personFound = personResository.findById(id).orElseThrow(() -> new PersonNotFoundException("No such person"));
        String oldEmail = personFound.getEmail();
        personFound.setEmail(email);
        Person updatedPerson = personResository.save(personFound);
        log.info("Updated person email: {} {} {}", oldEmail, " >>>>> ", updatedPerson.getEmail());
    }

    @Override
    public void deletePerson(Integer id) throws PersonNotFoundException {
        Person personFound = personResository.findById(id).orElseThrow(() -> new PersonNotFoundException("No such person"));
        personResository.delete(personFound);
        log.info("Delete successful : {}", personFound.getEmail());

    }
}
