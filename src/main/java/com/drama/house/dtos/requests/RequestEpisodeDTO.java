package com.drama.house.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestEpisodeDTO {
    private Long seasonId;
    private int episodeNumber;
    private String title;
    private String description;
    private MultipartFile videoFile;
    private MultipartFile posterFile;
    private int duration;
    private String releaseDate;
}
