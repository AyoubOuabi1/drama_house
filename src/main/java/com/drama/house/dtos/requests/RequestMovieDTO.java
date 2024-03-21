package com.drama.house.dtos.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RequestMovieDTO {

    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Release date is mandatory")
    private String releaseDate;

    @NotBlank(message = "Language is mandatory")
    private String language;

    @Min(value = 1, message = "Duration should not be less than 1")
    private int duration;

    @NotBlank(message = "Trailer URL is mandatory")
    private String trailerUrl;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MultipartFile videoFile;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MultipartFile posterFile;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private MultipartFile coverFile;

    @NotEmpty(message = "Genres list should not be empty")
    private List<Long> genres;

    @NotEmpty(message = "Cast list should not be empty")
    private List<Long> cast;

    @NotNull(message = "Director ID is mandatory")
    private Long directorId;
}