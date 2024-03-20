package com.drama.house.services;

import com.drama.house.dtos.GenreDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    List<GenreDTO> getAllGenres();
    GenreDTO saveGenre(GenreDTO genreDTO);
    GenreDTO getGenreById(Long id);
    void deleteGenre(Long id);
}
