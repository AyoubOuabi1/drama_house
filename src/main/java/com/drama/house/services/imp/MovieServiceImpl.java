package com.drama.house.services.imp;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;
import com.drama.house.entities.Genre;
import com.drama.house.entities.Movie;
import com.drama.house.entities.Person;
import com.drama.house.mappers.MovieMapper;
import com.drama.house.repositories.MovieRepository;
import com.drama.house.services.GenreService;
import com.drama.house.services.MovieService;
import com.drama.house.services.PersonService;
import com.drama.house.services.S3Service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final ModelMapper modelMapper;

    private final   S3Service s3Service;

    private final GenreService genreService;

    private final PersonService personService;

    public MovieServiceImpl(S3Service s3Service, MovieRepository movieRepository,
                            ModelMapper modelMapper, GenreService genreService,
                            PersonService personService) {
        this.s3Service = s3Service;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreService = genreService;
        this.personService = personService;
    }
    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> getFirstTenMovies() {
        Pageable topTen = PageRequest.of(0, 12);
        Page<Movie> movies = movieRepository.findLastTenMoviesAdded(topTen);
        return movies.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        return (movie != null) ? convertToDTO(movie) : null;
    }

    @Override
    public MovieDTO saveMovie(RequestMovieDTO requestMovieDTO) throws ParseException {
        Movie movie = convertToEntity(requestMovieDTO);
        movie = movieRepository.save(movie);
        MovieDTO movieDTO = convertToDTO(movie);
        movieDTO.setDirector(modelMapper.map(personService.getPersonById(requestMovieDTO.getDirectorId()), Person.class));
        return movieDTO;
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieDTO> findByName(String name) {

        return movieRepository.findMovieByName(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieDTO> findByGenre(String name) {
        return movieRepository.findMovieByGenre(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MovieDTO convertToDTO(Movie movie) {

        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        movieDTO.setDirector(movie.getDirector());
        return movieDTO;
    }

    private Movie convertToEntity(RequestMovieDTO requestMovieDTO) throws ParseException {
        Movie movie = MovieMapper.toMovie(requestMovieDTO);
        movie.setPosterUrl(s3Service.uploadFile("movies_images",requestMovieDTO.getPosterFile()));
        movie.setCoverUrl(s3Service.uploadFile("movies_images",requestMovieDTO.getCoverFile()));
        movie.setVideoUrl(s3Service.uploadFile("movies_videos",requestMovieDTO.getVideoFile()));
        List<Genre> genres = new ArrayList<>();
        requestMovieDTO.getGenres().forEach(genreId -> {
            Genre genre = modelMapper.map(genreService.getGenreById(genreId),Genre.class);
            genres.add(genre);
        });
        movie.setGenres(genres);
        Person director = modelMapper.map(personService.getPersonById(requestMovieDTO.getDirectorId()), Person.class);
        movie.setDirector(director);
        List<Person> cast = new ArrayList<>();
        requestMovieDTO.getCast().forEach(personId -> {
            Person person = modelMapper.map(personService.getPersonById(personId), Person.class);
            cast.add(person);
        });
        movie.setCast(cast);
        return movie;
    }


}

