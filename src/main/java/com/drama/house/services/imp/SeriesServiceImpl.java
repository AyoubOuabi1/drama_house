package com.drama.house.services.imp;

import com.drama.house.dtos.SeriesDTO;
import com.drama.house.dtos.requests.RequestSeriesDTO;
import com.drama.house.entities.Genre;
import com.drama.house.entities.Person;
import com.drama.house.entities.Series;
import com.drama.house.mappers.SeriesMapper;
import com.drama.house.repositories.SeriesRepository;
import com.drama.house.services.GenreService;
import com.drama.house.services.PersonService;
import com.drama.house.services.S3Service;
import com.drama.house.services.SeriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;


    private final ModelMapper modelMapper;

    private final S3Service s3Service;

    private final PersonService personService;

    private final GenreService genreService;

    public SeriesServiceImpl(SeriesRepository seriesRepository, ModelMapper modelMapper,
                             S3Service s3Service, PersonService personService, GenreService genreService) {
        this.seriesRepository = seriesRepository;
        this.modelMapper = modelMapper;
        this.s3Service = s3Service;
        this.personService = personService;
        this.genreService = genreService;
    }

    @Override
    public List<SeriesDTO> getAllSeries() {
        List<Series> seriesList = seriesRepository.findAll();
        return seriesList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeriesDTO saveSeries(RequestSeriesDTO seriesDTO) {
        Series series = convertToEntity(seriesDTO);
        series = seriesRepository.save(series);
        return convertToDTO(series);
    }

    @Override
    public SeriesDTO findById(Long id) {
        return convertToDTO(seriesRepository.findById(id).orElse(null));
    }

    private SeriesDTO convertToDTO(Series series) {
        return modelMapper.map(series, SeriesDTO.class);
    }

    private Series convertToEntity(RequestSeriesDTO seriesDTO) {
        Series series = SeriesMapper.toSeries(seriesDTO);
        series.setCoverUrl(s3Service.uploadFile("series_images",seriesDTO.getCoverImage()));
        series.setPosterUrl(s3Service.uploadFile("series_images",seriesDTO.getPosterImage()));
        List<Person> cast =  new ArrayList<>();
        seriesDTO.getCast().forEach(personId -> {
            cast.add(modelMapper.map(personService.getPersonById(personId), Person.class));
        });
        series.setCast(cast);
        series.setDirector(modelMapper.map(personService.getPersonById(seriesDTO.getDirectorId()), Person.class));
        List<Genre> genres=new ArrayList<>();
        seriesDTO.getGenres().forEach(genreId -> {
            genres.add(modelMapper.map(genreService.getGenreById(genreId), Genre.class));
        });
        series.setGenres(genres);
        return series;
    }
}

