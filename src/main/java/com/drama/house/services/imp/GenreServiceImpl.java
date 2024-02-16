package com.drama.house.services.imp;

import com.drama.house.dtos.GenreDTO;
import com.drama.house.entities.Genre;
import com.drama.house.repositories.GenreRepository;
import com.drama.house.services.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDTO saveGenre(GenreDTO genreDTO) {
        Genre genre = convertToEntity(genreDTO);
        genre = genreRepository.save(genre);
        return convertToDTO(genre);
    }

    private GenreDTO convertToDTO(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }

    private Genre convertToEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }
}

