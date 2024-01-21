package com.drama.house.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private String nationality;
    private String posterUrl;
    private String movieUrl;
    private LocalDate releaseDate;
    private Set<Long> categoryIds; // Representing the Category IDs
    // Add any other fields as needed
}