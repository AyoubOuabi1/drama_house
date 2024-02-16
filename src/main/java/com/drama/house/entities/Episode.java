package com.drama.house.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Series series;

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
