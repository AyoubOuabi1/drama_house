package com.drama.house.services.imp;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.requests.RequestMovieDTO;
import com.drama.house.entities.Genre;
import com.drama.house.entities.Movie;
import com.drama.house.entities.Person;
import com.drama.house.entities.Watchlist;
import com.drama.house.mappers.MovieMapper;
import com.drama.house.repositories.MovieRepository;
import com.drama.house.repositories.WatchListRepository;
import com.drama.house.services.GenreService;
import com.drama.house.services.MovieService;
import com.drama.house.services.PersonService;
import com.drama.house.services.S3Service;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.drama.house.exception.CustomException;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final S3Service s3Service;
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
        try {
            List<Movie> movies = movieRepository.findAll();
            return movies.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Failed to get all movies: " + e.getMessage());
        }
    }

    @Override
    public List<MovieDTO> getFirstTenMovies() {
        try {
            Pageable topTen = PageRequest.of(0, 12);
            Page<Movie> movies = movieRepository.findLastTenMoviesAdded(topTen);
            return movies.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Failed to get first ten movies: " + e.getMessage());
        }
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        try {
            Movie movie = movieRepository.findById(id).orElse(null);
            return (movie != null) ? convertToDTO(movie) : null;
        } catch (Exception e) {
            throw new CustomException("Failed to get movie by ID: " + e.getMessage());
        }
    }

    @Override
    public MovieDTO saveMovie(RequestMovieDTO requestMovieDTO) throws ParseException {
        try {
            Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
            LOGGER.info("ayoub: {}", requestMovieDTO);
            Movie movie = new Movie();
            movie = convertToEntity(requestMovieDTO, movie);
            movie = movieRepository.save(movie);
            MovieDTO movieDTO = convertToDTO(movie);
            movieDTO.setDirector(modelMapper.map(personService.getPersonById(requestMovieDTO.getDirectorId()), Person.class));
            return movieDTO;
        } catch (Exception e) {
            throw new CustomException("Failed to save movie: " + e.getMessage());
        }
    }

    @Override
    public Movie saveMovie(Movie requestMovieDTO) {
        try {
            return movieRepository.save(requestMovieDTO);
        } catch (Exception e) {
            throw new CustomException("Failed to save movie: " + e.getMessage());
        }
    }

    @Override
    public MovieDTO updateMovie(RequestMovieDTO requestMovieDTO) throws ParseException {
        try {
            Movie movie = movieRepository.findById(requestMovieDTO.getId()).orElseThrow(() -> new CustomException("Movie not found"));
            movie = convertToEntity(requestMovieDTO, movie);
            movie = movieRepository.save(movie);
            return convertToDTO(movie);
        } catch (Exception e) {
            throw new CustomException("Failed to update movie: " + e.getMessage());
        }
    }

    @Override
    public List<Movie> findAllByCastContains(Person person) {
        try {
            return movieRepository.findAllByCastContains(person);
        } catch (Exception e) {
            throw new CustomException("Failed to find movies by cast: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteMovie(Long id) {
        try {
            Movie movie = movieRepository.findById(id).orElseThrow(() -> new CustomException("Movie not found"));
            movieRepository.delete(movie);
        } catch (Exception e) {
            throw new CustomException("Failed to delete movie: " + e.getMessage());
        }
    }

    @Override
    public List<MovieDTO> findByName(String name) {
        try {
            return movieRepository.findMovieByName(name).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Failed to find movies by name: " + e.getMessage());
        }
    }

    @Override
    public List<MovieDTO> findByGenre(String name) {
        try {
            return movieRepository.findMovieByGenre(name).stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Failed to find movies by genre: " + e.getMessage());
        }
    }

    private MovieDTO convertToDTO(Movie movie) {
        try {
            MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
            movieDTO.setDirector(movie.getDirector());
            return movieDTO;
        } catch (Exception e) {
            throw new CustomException("Failed to convert movie to DTO: " + e.getMessage());
        }
    }

    private Movie convertToEntity(RequestMovieDTO requestMovieDTO, Movie movie) throws ParseException {
        try {
            movie.setTitle(requestMovieDTO.getTitle());
            movie.setDescription(requestMovieDTO.getDescription());
            movie.setReleaseDate(requestMovieDTO.getReleaseDate());
            movie.setLanguage(requestMovieDTO.getLanguage());
            movie.setDuration(requestMovieDTO.getDuration());
            movie.setTrailerUrl(requestMovieDTO.getTrailerUrl());
            if (requestMovieDTO.getPosterFile() != null && !requestMovieDTO.getPosterFile().isEmpty()) {
                movie.setPosterUrl(s3Service.uploadFile("movies_images",requestMovieDTO.getPosterFile()));
            }
            if (requestMovieDTO.getCoverFile() != null && !requestMovieDTO.getCoverFile().isEmpty()) {
                movie.setCoverUrl(s3Service.uploadFile("movies_images",requestMovieDTO.getCoverFile()));
            }
            if (requestMovieDTO.getVideoFile() != null && !requestMovieDTO.getVideoFile().isEmpty()) {
                movie.setVideoUrl(s3Service.uploadFile("movies_videos",requestMovieDTO.getVideoFile()));
            }
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
        } catch (Exception e) {
            throw new CustomException("Failed to convert request movie DTO to entity: " + e.getMessage());
        }
    }
}
