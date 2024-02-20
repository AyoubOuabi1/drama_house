package com.drama.house.mappers;

import com.drama.house.dtos.requests.RequestMovieDTO;
import com.drama.house.entities.Movie;

public class MovieMapper {


    public static Movie toMovie(RequestMovieDTO requestMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(requestMovieDTO.getTitle());
        movie.setDescription(requestMovieDTO.getDescription());
        movie.setReleaseDate(requestMovieDTO.getReleaseDate());
        movie.setDuration(requestMovieDTO.getDuration());
        movie.setTrailerUrl(requestMovieDTO.getTrailerUrl());
        return movie;
    }
}
