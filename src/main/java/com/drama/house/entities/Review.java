package com.drama.house.entities;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = true)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = true)
    private Episode episode;

    private int rating;

}
