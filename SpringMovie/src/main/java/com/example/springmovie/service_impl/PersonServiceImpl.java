package com.example.springmovie.service_impl;

import com.example.springmovie.model.Person;
import com.example.springmovie.repositories.PersonRepository;
import com.example.springmovie.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor


@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonById(Long id) {
        // TODO: throw supplier
        return personRepository.findById(id).orElseThrow();
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> findPersonByName(String name) {
        return null;
    }
}
