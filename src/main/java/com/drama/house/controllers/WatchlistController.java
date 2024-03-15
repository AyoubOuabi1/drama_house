package com.drama.house.controllers;

import com.drama.house.dtos.MovieDTO;
import com.drama.house.dtos.WatchlistDTO;
import com.drama.house.dtos.requests.RequestWatchlistDTO;
import com.drama.house.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/watch-list/")
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @GetMapping
    public WatchlistDTO getWatchListByUser() {
        return watchlistService.getWatchlistByUserId();
    }


    @PostMapping("add/{movieId}")
    public String addWatchlist(@PathVariable Long movieId) {
        return watchlistService.saveWatchlist(movieId);
    }

    @DeleteMapping("delete-movie")
    public String removeMovieFromWatchlist(@RequestParam Long movieId) {
        return watchlistService.removeMovieFromWatchlist(movieId);
    }

    @GetMapping("check-movie/{movieId}")
    public boolean checkIfMovieExistsInWatchlist(@PathVariable Long movieId) {
        return watchlistService.checkIfMovieExistsInWatchlist(movieId);
    }

}
