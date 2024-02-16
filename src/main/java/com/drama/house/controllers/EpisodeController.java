package com.drama.house.controllers;

import com.drama.house.dtos.EpisodeDTO;
import com.drama.house.services.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {
    @Autowired
    private EpisodeService episodeService;

    @GetMapping
    public List<EpisodeDTO> getAllEpisodes() {
        return episodeService.getAllEpisodes();
    }

    @PostMapping
    public EpisodeDTO addEpisode(@RequestBody EpisodeDTO episodeDTO) {
        return episodeService.saveEpisode(episodeDTO);
    }
}

