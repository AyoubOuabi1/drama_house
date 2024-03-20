package com.drama.house.controllers;

import com.drama.house.dtos.GenreDTO;
import com.drama.house.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping
    public GenreDTO addGenre(@RequestBody GenreDTO genreDTO) {
        return genreService.saveGenre(genreDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}

