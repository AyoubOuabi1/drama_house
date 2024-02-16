package com.drama.house.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nationality;
    private String biography;
    private Date birthDate;

}
