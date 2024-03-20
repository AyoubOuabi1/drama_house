package com.drama.house.controllers;

import com.drama.house.dtos.EpisodeDTO;
import com.drama.house.dtos.requests.RequestEpisodeDTO;
import com.drama.house.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/episodes")
public class EpisodeController {
    private final EpisodeService episodeService;
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }
    @GetMapping
    public List<EpisodeDTO> getAllEpisodes() {
        return episodeService.getAllEpisodes();
    }

    @PostMapping
    public EpisodeDTO addEpisode(@ModelAttribute RequestEpisodeDTO episodeDTO) {
        return episodeService.saveEpisode(episodeDTO);
    }
}

