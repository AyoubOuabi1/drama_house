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
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private String releaseDate;
    private String language;
    private int duration;
    private String trailerUrl;
    private String videoUrl;
    private String posterUrl;
    private String coverUrl;

    @ManyToMany
    private List<Genre> genres;

    @ManyToMany
    private List<Person> cast;

    @ManyToOne
    private Person director;

}
