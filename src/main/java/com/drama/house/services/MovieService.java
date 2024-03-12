package com.drama.house.services;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;

public interface MovieService {
    MovieDTO getMovieById(Long id);
    MovieDTO saveMovie(RequestMovieDTO requestMovieDTO) throws ParseException;
    void deleteMovie(Long id);

    Page<MovieDTO> findAllMovies(Pageable pageable);
    Page<MovieDTO> findMovieByName(String name, Pageable pageable);
    Page<MovieDTO> findMovieByGenre(String name, Pageable pageable);
    List<MovieDTO> findLastTenMoviesAdded();
}
