package com.example.backend.services;

import com.example.backend.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAllPersons();

    Person findPersonById(Long id);

    Person savePerson(Person person);

    Person updatePerson(Person person);

    void deletePersonById(Long id);

    List<Person> findPersonByName(String name);
}
