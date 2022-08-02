package com.example.demo.api;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PersonService;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @GetMapping(path = "/{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public int deletePersonById(@PathVariable("id") UUID id){
        return personService.deletePersonById(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @RequestBody Person person){
        personService.updatePersonById(id, person);
    }
}
