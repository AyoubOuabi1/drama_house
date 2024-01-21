package com.drama.house.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

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
@DiscriminatorValue("Serie")
public class Serie extends Movie {
    private int numberOfSeasons;
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private Set<Episode> episodes = new HashSet<>();


}

