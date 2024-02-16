package com.drama.house.services;

import com.drama.house.dtos.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPersons();
    PersonDTO savePerson(PersonDTO personDTO);
}
