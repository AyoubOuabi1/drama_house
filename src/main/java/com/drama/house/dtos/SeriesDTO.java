package com.drama.house.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
    private MultipartFile coverImage;
    private MultipartFile posterImage;
    private List<GenreDTO> genres;
    private List<PersonDTO> cast;
    private PersonDTO director;
}

