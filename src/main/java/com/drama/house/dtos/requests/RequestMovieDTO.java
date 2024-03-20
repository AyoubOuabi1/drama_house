package com.drama.house.dtos.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestMovieDTO {

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private String title;
    private String description;
    private String releaseDate;
    private String language;
    private int duration;
    private String trailerUrl;
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile videoFile;
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile posterFile;
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile coverFile;
    private List<Long> genres;
    private List<Long> cast;
    private Long directorId;


    public void setGenres(String[] genres) {
        this.genres = convertToLongList(genres);
    }

    public void setCast(String[] cast) {
        this.cast = convertToLongList(cast);
    }

    private List<Long> convertToLongList(String[] array) {
        return Arrays.stream(array)
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
