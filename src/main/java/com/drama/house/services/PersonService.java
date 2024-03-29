package com.drama.house.services;

import com.drama.house.dtos.PersonDTO;
import com.drama.house.dtos.requests.RequestPersonDTO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface PersonService {
    List<PersonDTO> getAllPersons();
    PersonDTO savePerson(RequestPersonDTO personDTO) throws ParseException;
    PersonDTO getPersonById(Long id);

    List<PersonDTO> findByName(String name);

     void deletePerson(Long id);
}
