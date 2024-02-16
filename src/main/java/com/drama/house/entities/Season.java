package com.drama.house.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Series series;

    private int number;
    private Date releaseDate;

    // Constructors, getters, and setters
}
