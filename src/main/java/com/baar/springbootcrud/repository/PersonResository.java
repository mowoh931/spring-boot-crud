package com.baar.springbootcrud.repository;


import com.baar.springbootcrud.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonResository extends JpaRepository<Person, Integer> {

    Optional<Person> findByLastName(String lastName);
}
