package com.drama.house.services;

import com.drama.house.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    MovieDTO getMovieById(Long movieId);
    List<MovieDTO> getAllMovies();
    MovieDTO createMovie(MovieDTO movieDTO);
    MovieDTO updateMovie(Long movieId, MovieDTO movieDTO);
    void deleteMovie(Long movieId);
}
