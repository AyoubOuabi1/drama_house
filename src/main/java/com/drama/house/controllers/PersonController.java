package com.drama.house.controllers;

import com.drama.house.dtos.PersonDTO;
import com.drama.house.dtos.requests.RequestPersonDTO;
import com.drama.house.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public PersonDTO addPerson(@ModelAttribute RequestPersonDTO personDTO) throws ParseException {
        return personService.savePerson(personDTO);
    }
}

