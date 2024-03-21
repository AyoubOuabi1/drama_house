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
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import com.drama.house.exception.CustomException;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final S3Service s3Service;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper, S3Service s3Service) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.s3Service = s3Service;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        try {
            List<Person> persons = personRepository.findAll();
            return persons.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Failed to retrieve all persons: " + e.getMessage());
        }
    }

    @Override
    public PersonDTO savePerson(RequestPersonDTO personDTO) throws ParseException {
        try {
            Person person = convertToEntity(personDTO);
            person = personRepository.save(person);
            return convertToDTO(person);
        } catch (Exception e) {
            throw new CustomException("Failed to save person: " + e.getMessage());
        }
    }

    @Override
    public PersonDTO getPersonById(Long id) {
        try {
            return personRepository.findById(id)
                    .map(this::convertToDTO)
                    .orElse(null);
        } catch (Exception e) {
            throw new CustomException("Failed to retrieve person by ID: " + e.getMessage());
        }
    }

    @Override
    public List<PersonDTO> findByName(String name) {
        try {
            return personRepository.findByName(name).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Failed to find persons by name: " + e.getMessage());
        }
    }

    @Transactional
    public void deletePerson(Long personId) {
        try {
            Person person = personRepository.findById(personId).orElseThrow(() -> new CustomException("Person not found"));
              /*  List<Movie> movies = movieService.findAllByCastContains(person);
        for (Movie movie : movies) {
            movie.getCast().remove(person);
            movieService.save(movie);
        }*/
            personRepository.delete(person);
        } catch (Exception e) {
            throw new CustomException("Failed to delete person: " + e.getMessage());
        }

    }

    private PersonDTO convertToDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    private Person convertToEntity(RequestPersonDTO requestPersonDTO) throws ParseException {
        Person person = PersonMapper.toPerson(requestPersonDTO);
        person.setImageUrl(s3Service.uploadFile("persons_images",requestPersonDTO.getImageFile()));
        return person;
    }
}
