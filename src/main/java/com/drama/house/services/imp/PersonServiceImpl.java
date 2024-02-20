package com.drama.house.services.imp;

import com.drama.house.dtos.PersonDTO;
import com.drama.house.dtos.requests.RequestPersonDTO;
import com.drama.house.entities.Person;
import com.drama.house.mappers.PersonMapper;
import com.drama.house.repositories.PersonRepository;
import com.drama.house.services.PersonService;
import com.drama.house.services.S3Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    private final ModelMapper modelMapper;

    private final S3Service s3Service;

    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper, S3Service s3Service) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.s3Service = s3Service;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO savePerson(RequestPersonDTO personDTO) throws ParseException {
        Person person = convertToEntity(personDTO);
        person = personRepository.save(person);
        return convertToDTO(person);
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        return personRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private PersonDTO convertToDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    private Person convertToEntity(RequestPersonDTO requestPersonDTO) throws ParseException {
        Person person = PersonMapper.toPerson(requestPersonDTO);
        person.setImageUrl(s3Service.uploadFile(requestPersonDTO.getImageFile()));
        return person;
    }
}

