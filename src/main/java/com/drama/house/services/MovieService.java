package com.drama.house.services;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;
import com.drama.house.entities.Movie;
import com.drama.house.entities.Person;

import java.text.ParseException;
import java.util.List;

public interface MovieService {
    List<MovieDTO> getAllMovies();
    List<MovieDTO> getFirstTenMovies();
    MovieDTO getMovieById(Long id);
    MovieDTO saveMovie(RequestMovieDTO requestMovieDTO) throws ParseException;
    Movie saveMovie(Movie requestMovieDTO);
    void deleteMovie(Long id);

    List<MovieDTO> findByName(String name);
    List<MovieDTO> findByGenre(String name);

    MovieDTO updateMovie(RequestMovieDTO requestMovieDTO) throws ParseException;
    List<Movie> findAllByCastContains(Person person);

}

