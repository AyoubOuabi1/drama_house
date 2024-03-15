package com.drama.house.dtos;

import com.drama.house.entities.Movie;
import com.drama.house.entities.Series;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WatchlistDTO {

    private Long id;
    private List<Movie> movies;
}
