package com.drama.house.controllers;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;
import com.drama.house.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public Page<MovieDTO> getAllMovies(Pageable pageable) {
        return movieService.findAllMovies(pageable);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieDTO addMovie(@ModelAttribute RequestMovieDTO requestMovieDTO) throws ParseException {
        return movieService.saveMovie(requestMovieDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/search/{name}")
    public Page<MovieDTO> findByName(@PathVariable String name, Pageable pageable) {
        return movieService.findMovieByName(name, pageable);
    }

    @GetMapping("/search/genre/{name}")
    public Page<MovieDTO> findByGenre(@PathVariable String name, Pageable pageable) {
        return movieService.findMovieByGenre(name, pageable);
    }

    @GetMapping("/last-ten")
    public List<MovieDTO> getFirstTenMovies() {
        return movieService.findLastTenMoviesAdded();
    }
}
