package com.drama.house.services.imp;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.WatchlistDTO;
import com.drama.house.dtos.requests.RequestWatchlistDTO;
import com.drama.house.entities.Movie;
import com.drama.house.entities.User;
import com.drama.house.entities.Watchlist;
import com.drama.house.repositories.WatchListRepository;
import com.drama.house.services.MovieService;
import com.drama.house.services.UserService;
import com.drama.house.services.WatchlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistServiceImpl implements WatchlistService {
    private WatchListRepository watchlistRepository;


    private ModelMapper modelMapper;
    UserService userService;
    MovieService movieService;

    public WatchlistServiceImpl(WatchListRepository watchlistRepository,
                                ModelMapper modelMapper,
                                UserService userService,
                                MovieService movieService) {
        this.watchlistRepository = watchlistRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.movieService = movieService;
    }

    @Override
    public List<WatchlistDTO> getAllWatchlists() {
        List<Watchlist> watchlists = watchlistRepository.findAll();
        return watchlists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String saveWatchlist(Long movieId) {
        User user = userService.getCurrentUser();
        Movie movie = modelMapper.map(movieService.getMovieById(movieId), Movie.class);

        if (user == null || movie == null) {
            return "User or movie not found";
        }

        Watchlist watchlist = user.getWatchlist();
        if (watchlist == null) {
            watchlist = new Watchlist();
            watchlist.setUser(user);
        }

        boolean movieAlreadyExists = watchlistRepository.existsMovieInWatchlist(user.getId(), movie.getId());

        if (!movieAlreadyExists) {
            watchlist.getMovies().add(movie);
            watchlistRepository.save(watchlist);
            return "Movie added to watchlist";
        } else {
            return "Movie already exists in watchlist";
        }
    }

    @Override
    public boolean checkIfMovieExistsInWatchlist(Long movieId) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return false;
        }

        Watchlist watchlist = user.getWatchlist();
        if (watchlist == null) {
            return false;
        }

        return watchlistRepository.existsMovieInWatchlist(user.getId(), movieId);
    }

    @Override
    public WatchlistDTO getWatchlistByUserId() {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Watchlist watchlist = user.getWatchlist();
        if (watchlist == null) {
            throw new RuntimeException("Watchlist not found for user");
        }

        // Convert the Watchlist entity to a WatchlistDTO
        WatchlistDTO watchlistDTO = modelMapper.map(watchlist, WatchlistDTO.class);

        return watchlistDTO;
    }

    @Override
    public String removeMovieFromWatchlist(Long movieId) {
        User user = userService.getCurrentUser();
        if (user == null) {
            return "User not found";
        }

        Watchlist watchlist = user.getWatchlist();
        if (watchlist == null) {
            return "No watchlist found for user";
        }

        Movie movieToRemove = watchlist.getMovies().stream()
                .filter(m -> m.getId().equals(movieId))
                .findFirst()
                .orElse(null);

        if (movieToRemove == null) {
            return "Movie not found in watchlist";
        }

        watchlist.getMovies().remove(movieToRemove);
        watchlistRepository.save(watchlist);

        return "Movie removed from watchlist";
    }

    private WatchlistDTO convertToDTO(Watchlist watchlist) {
        return modelMapper.map(watchlist, WatchlistDTO.class);
    }

    private Watchlist convertToEntity(WatchlistDTO watchlistDTO) {
        return modelMapper.map(watchlistDTO, Watchlist.class);
    }

    private MovieDTO convertToDTO(Movie movie) {

        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        movieDTO.setDirector(movie.getDirector());
        return movieDTO;
    }

}

