package com.drama.house.services;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;

import java.text.ParseException;
import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();
    MovieDTO getMovieById(Long id);
    MovieDTO saveMovie(RequestMovieDTO requestMovieDTO) throws ParseException;
    void deleteMovie(Long id);
}

