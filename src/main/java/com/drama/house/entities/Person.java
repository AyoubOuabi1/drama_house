package com.drama.house.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    private String nationality;
    @Column(columnDefinition = "text")
    private String biography;
    private Date birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "director", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Movie> directedMovies;
}
