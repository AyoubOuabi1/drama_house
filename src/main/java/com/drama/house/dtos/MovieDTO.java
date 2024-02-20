package com.drama.house.dtos;

import com.drama.house.entities.Genre;
import com.drama.house.entities.Person;
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
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private String releaseDate;
    private int duration;
    private String trailerUrl;
    private String videoUrl;
    private String posterUrl;
    private String coverUrl;
    private List<Genre> genres;
    private List<Person> cast;
    private Person directorId;
}
