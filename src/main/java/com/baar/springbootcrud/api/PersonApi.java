package com.baar.springbootcrud.api;


import com.baar.springbootcrud.dto.PersonDto;
import com.baar.springbootcrud.exception.PersonAlreadtExistsException;
import com.baar.springbootcrud.exception.PersonNotFoundException;
import com.baar.springbootcrud.model.Person;
import com.baar.springbootcrud.service.PersonServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonApi {
    private final PersonServiceImpl personService;

    public PersonApi(PersonServiceImpl personService) {
        this.personService = personService;

    }

    @ApiResponse(description = "Saves person information in the database")
    @PostMapping(value = "/save/person", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(@RequestBody(required = true) PersonDto personDto) throws PersonAlreadtExistsException {
        Person savedPerson = personService.savePerson(personDto);
        return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @ApiResponse(description = "Returns a list of people")
    @GetMapping(value = "/get/all/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDto>> getPersons() {
        List<PersonDto> persons = personService.getPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @ApiResponse(description = "Returns a person with specified last name")
    @GetMapping(value = "/get/person/lastName/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> findByLastName(@PathVariable(name = "lastName") String lastName) throws PersonNotFoundException {
        Person person = personService.findByLastName(lastName);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @ApiResponse(description = "Updates a person's email for given id")
    @PutMapping(value = "/update/person/id/email")
    public ResponseEntity<Void> updatePersonEmail(@RequestParam(name = "id") Integer id, @RequestParam(name = "email") String email) throws PersonNotFoundException {
        personService.updatePersonEmail(id, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiResponse(description = "Delete a person for given id")
    @DeleteMapping(value = "/delete/person/id/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable(name = "id") Integer id) throws PersonNotFoundException {
        personService.deletePerson(id);
        return new ResponseEntity<>("Delete successfully completed ", HttpStatus.CREATED);
    }

}
