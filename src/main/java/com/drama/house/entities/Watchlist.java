package com.drama.house.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    private List<Movie> movies;

    @ManyToMany
    private List<Series> series;

    // Constructors, getters, and setters
}
