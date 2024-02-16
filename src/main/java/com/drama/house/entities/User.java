package com.drama.house.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    // Other fields

    @ManyToMany
    private List<Movie> favoriteMovies;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    // Other mappings and getters/setters
}
