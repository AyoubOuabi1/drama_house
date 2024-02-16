package com.drama.house.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private int numberOfSeasons;
    private String format;
    private float rating;
    private int numRatings;
    private boolean isAvailable;

    @ManyToMany
    private List<Genre> genres;

    @ManyToMany
    private List<Person> cast;

    @ManyToOne
    private Person director;

    @OneToMany(mappedBy = "series")
    private List<Season> seasons;

    // Constructors, getters, and setters
}
