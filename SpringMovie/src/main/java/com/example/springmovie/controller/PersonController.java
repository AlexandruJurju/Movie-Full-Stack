package com.example.springmovie.controller;


import com.example.springmovie.model.Person;
import com.example.springmovie.service.PersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Tag(name = "Person Controller")

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> findAllPersons() {
        List<Person> persons = personService.findAllPersons();
        if (persons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }
}
