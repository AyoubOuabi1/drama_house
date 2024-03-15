package com.drama.house.services;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.WatchlistDTO;
import com.drama.house.dtos.requests.RequestWatchlistDTO;

import java.util.List;

public interface WatchlistService {
    List<WatchlistDTO> getAllWatchlists();
    String saveWatchlist(Long movieId);
    WatchlistDTO getWatchlistByUserId();
    String removeMovieFromWatchlist(Long movieId);

    boolean checkIfMovieExistsInWatchlist(Long movieId);

}
