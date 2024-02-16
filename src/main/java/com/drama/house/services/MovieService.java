package com.drama.house.services;

import com.drama.house.dtos.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();
    MovieDTO getMovieById(Long id);
    MovieDTO saveMovie(MovieDTO movieDTO);
    void deleteMovie(Long id);
}

