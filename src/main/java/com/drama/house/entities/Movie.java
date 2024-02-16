package com.drama.house.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private int duration;
    private String format;
    private String videoUrl;

    @ManyToMany
    private List<Genre> genres;

    @ManyToMany
    private List<Person> cast;

    @ManyToOne
    private Person director;

}
