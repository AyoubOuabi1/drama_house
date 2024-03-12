package com.drama.house.controllers;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;
import com.drama.house.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
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

    @GetMapping("/search")
    public List<MovieDTO> findByName(@RequestParam String name) {
        return movieService.findByName(name);
    }

    @GetMapping("/search/genre")
    public List<MovieDTO> findByGenre(@RequestParam String name) {
        return movieService.findByGenre(name);
    }

    @GetMapping("/last-ten")
    public List<MovieDTO> getFirstTenMovies() {
        return movieService.getFirstTenMovies();
    }
}

