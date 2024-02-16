package com.drama.house.services;

import com.drama.house.dtos.GenreDTO;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getAllGenres();
    GenreDTO saveGenre(GenreDTO genreDTO);
}
