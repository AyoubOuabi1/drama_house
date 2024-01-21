package com.drama.house.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    String imageUrl;
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    private String nationality;

    private String bio;

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
    private Set<ActorMovieRole> actorMovieRoles = new HashSet<>();

}

