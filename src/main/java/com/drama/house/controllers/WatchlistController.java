package com.drama.house.controllers;

import com.drama.house.dtos.WatchlistDTO;
import com.drama.house.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/watchlists")
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @GetMapping
    public List<WatchlistDTO> getAllWatchlists() {
        return watchlistService.getAllWatchlists();
    }

    @PostMapping
    public WatchlistDTO addWatchlist(@RequestBody WatchlistDTO watchlistDTO) {
        return watchlistService.saveWatchlist(watchlistDTO);
    }
}
