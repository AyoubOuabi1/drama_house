package com.drama.house.controllers;

import com.drama.house.dtos.SeasonDTO;
import com.drama.house.dtos.requests.RequestSeasonDTO;
import com.drama.house.services.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/seasons")
public class SeasonController {
    private SeasonService seasonService;
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }
    @GetMapping
    public List<SeasonDTO> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    @PostMapping
    public SeasonDTO addSeason(@ModelAttribute RequestSeasonDTO seasonDTO) {
        return seasonService.saveSeason(seasonDTO);
    }
}

