package com.drama.house.dtos;

import com.drama.house.entities.Series;
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
public class SeasonDTO {
    private Long id;
    private String title;
    private String seriesName;
    private int numberOfEpisodes;
    private String releaseDate;
    private String coverUrl;
    private String posterUrl;


}