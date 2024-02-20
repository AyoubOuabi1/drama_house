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
    private String series;
    private String season;
    private int episodeNumber;
    private String title;
    private String description;
    private String videoUrl;
    private String posterUrl;
    private int duration;
    private String releaseDate;


}
