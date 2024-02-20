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
public class SeasonDTO {
    private Long id;
    private Long seriesId;
    private int number;
    private Date releaseDate;
    private MultipartFile coverFile;
}