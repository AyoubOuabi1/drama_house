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
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int episodeNumber;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private String videoUrl;
    private String posterUrl;
    private int duration;
    private String releaseDate;

    @ManyToOne
    private Season season;

}
