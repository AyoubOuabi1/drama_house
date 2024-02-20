package com.drama.house.dtos.requests;

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
public class RequestMovieDTO {
    private String title;
    private String description;
    private String releaseDate;
    private int duration;
    private String trailerUrl;
    private MultipartFile videoFile;
    private MultipartFile posterFile;
    private MultipartFile coverFile;
    private List<Long> genres;
    private List<Long> cast;
    private Long directorId;
}
