package com.drama.house.dtos;

import java.util.Date;
import java.util.List;

public class SeriesDTO {
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private int numberOfSeasons;
    private String format;
    private float rating;
    private int numRatings;
    private boolean isAvailable;
    private List<GenreDTO> genres;
    private List<PersonDTO> cast;
    private PersonDTO director;
}

