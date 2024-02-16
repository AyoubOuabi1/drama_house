package com.drama.house.dtos;

import java.util.Date;
import java.util.List;

public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private int duration;
    private String format;
    private String videoUrl;
    private float rating;
    private int numRatings;
    private boolean isAvailable;
    private List<GenreDTO> genres;
    private List<PersonDTO> cast;
    private PersonDTO director;
    // Constructors, getters, and setters
}
