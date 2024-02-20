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
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private int numberOfEpisodes;
    private String releaseDate;
    private String posterUrl;
    private String coverUrl;
    @ManyToOne
    private Series series;

    @OneToMany(mappedBy = "season")
    private List<Episode> episodes;

}
