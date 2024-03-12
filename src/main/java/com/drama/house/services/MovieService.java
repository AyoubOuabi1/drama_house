package com.drama.house.services;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;

import java.text.ParseException;
import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();
    List<MovieDTO> getFirstTenMovies();
    MovieDTO getMovieById(Long id);
    MovieDTO saveMovie(RequestMovieDTO requestMovieDTO) throws ParseException;
    void deleteMovie(Long id);

    List<MovieDTO> findByName(String name);
    List<MovieDTO> findByGenre(String name);
}

