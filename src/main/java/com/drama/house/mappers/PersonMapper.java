package com.drama.house.mappers;


import com.drama.house.dtos.PersonDTO;
import com.drama.house.dtos.requests.RequestPersonDTO;
import com.drama.house.entities.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class PersonMapper {

    public static Person toPerson(RequestPersonDTO personDTO) throws ParseException {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setNationality(personDTO.getNationality());
        person.setBiography(personDTO.getBiography());
        person.setBirthDate(new SimpleDateFormat("yyyy-mm-dd")
                                .parse(personDTO.getBirthDate()));
        return person;
    }

    public static PersonDTO toPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setNationality(person.getNationality());
        personDTO.setBiography(person.getBiography());
        personDTO.setBirthDate(person.getBirthDate());
        return personDTO;
    }
}
