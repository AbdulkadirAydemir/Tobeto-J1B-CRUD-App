package com.tobeto.spring_1b;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/persons")

public class PersonsController {

    List<Person> inMemoryList = new ArrayList<>();

    @GetMapping("{id}")
    public Person getById(@PathVariable int id){
        Person person = inMemoryList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst()
                .orElseThrow();
        return person;
    }

    @PostMapping
    public Person post(@RequestBody Person person){
        inMemoryList.add(person);
        return person;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> personUpdate(@PathVariable int id, @RequestBody Person person) {
        Optional<Person> existingPerson = inMemoryList.stream().filter(p -> p.getId() == id).findFirst();

        if (existingPerson.isPresent()) {
            Person newPerson = existingPerson.get();
            newPerson.setId(person.getId());
            newPerson.setName(person.getName());
            newPerson.setSurname(person.getSurname());
            newPerson.setAge(person.getAge());
            return new ResponseEntity<>("Kişi güncellendi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bu ID'ye sahip kişi bulunamadı", HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("{id}")
    public Person deleteById(@PathVariable int id){
        Person person = inMemoryList
                .stream()
                .filter((p) -> p.getId() == id)
                .findFirst() // {}
                .orElseThrow();
        return person;
    }
}
