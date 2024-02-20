package com.drama.house.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private int numberOfSeasons;
    private String format;
    private float rating;
    private int numRatings;
    private boolean isAvailable;
    private String posterUrl;
    private String coverUrl;
    @ManyToMany
    private List<Genre> genres;

    @ManyToMany
    private List<Person> cast;

    @ManyToOne
    private Person director;

    @OneToMany(mappedBy = "series")
    private List<Season> seasons;
}
