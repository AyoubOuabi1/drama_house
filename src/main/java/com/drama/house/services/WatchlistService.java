package com.drama.house.services;

import com.drama.house.dtos.WatchlistDTO;

import java.util.List;

public interface WatchlistService {
    List<WatchlistDTO> getAllWatchlists();
    WatchlistDTO saveWatchlist(WatchlistDTO watchlistDTO);
}
