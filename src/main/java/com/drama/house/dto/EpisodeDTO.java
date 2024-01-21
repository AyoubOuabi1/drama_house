package com.drama.house.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeDTO {
    private Long id;
    private int duration;
    private int season;
    private String episodeUrl;
    private Long serieId; // Representing the Serie ID
    // Add any other fields as needed
}
