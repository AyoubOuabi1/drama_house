package com.drama.house.dtos.requests;

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
public class RequestSeasonDTO {
    private String title;
    private Long seriesId;
    private int numberOfEpisodes;
    private String releaseDate;
    private MultipartFile coverFile;
    private MultipartFile posterFile;


}