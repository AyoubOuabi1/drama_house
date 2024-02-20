package com.drama.house.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EpisodeDTO {
    private Long id;
    private Long seriesId;
    private int seasonNumber;
    private int episodeNumber;
    private String title;
    private String description;
    private MultipartFile videoFile;
    private MultipartFile posterFile;
    private int duration;
    private Date releaseDate;
    private float rating;
    private int numRatings;

}
