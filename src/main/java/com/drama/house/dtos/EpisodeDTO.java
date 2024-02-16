package com.drama.house.dtos;

import java.util.Date;

public class EpisodeDTO {
    private Long id;
    private Long seriesId;
    private int seasonNumber;
    private int episodeNumber;
    private String title;
    private String description;
    private String videoUrl;
    private int duration;
    private Date releaseDate;
    private float rating;
    private int numRatings;

}
