package com.drama.house.dtos.requests;

import com.drama.house.dtos.GenreDTO;
import com.drama.house.dtos.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestSeriesDTO {
    private String title;
    private String description;
    private String releaseDate;
    private String language;
    private String trailerUrl;
    private int numberOfSeasons;
    private MultipartFile coverImage;
    private MultipartFile posterImage;
    private List<Long> genres;
    private List<Long> cast;
    private Long directorId;
}

