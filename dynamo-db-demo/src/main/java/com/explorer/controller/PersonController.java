package com.explorer.controller;

import com.explorer.entity.Person;
import com.explorer.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PersonController {

    @GetMapping("/ping")
    public String pingRequest(){
        return "pong";
    }

    @Autowired
    private PersonRepository personRepository;
    @PostMapping("/savePerson")
    public void savePerson(@RequestBody Person person){
        personRepository.savePerson(person);
    }

    @GetMapping("/getPerson/{personId}")
    public Person findPersonById(@PathVariable String personId){
        return personRepository.getPersonById(personId);
    }

    @DeleteMapping("/deletePerson/{personId}")
    public void deletePerson(@PathVariable String personId){
        personRepository.deletePerson(personId);
    }

    @PutMapping("/editPerson")
    public void updatePerson(@RequestBody Person person){
        personRepository.updatePerson(person);
    }

}
