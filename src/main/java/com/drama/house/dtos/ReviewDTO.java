package com.drama.house.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long movieId; // OR episodeId
    private String content;
    private int rating;

    // Constructors, getters, and setters
}
