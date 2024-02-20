package com.drama.house.services;

import com.drama.house.dtos.PersonDTO;
import com.drama.house.dtos.requests.RequestPersonDTO;

import java.text.ParseException;
import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPersons();
    PersonDTO savePerson(RequestPersonDTO personDTO) throws ParseException;
    PersonDTO getPersonById(Long id);
}
