package com.drama.house.repositories;

import com.drama.house.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    @Query("SELECT p FROM Person p WHERE p.name like %:name%")
    List<Person> findByName(String name);
}
