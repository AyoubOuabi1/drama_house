package com.drama.house.services;

import com.drama.house.dtos.EpisodeDTO;

import java.util.List;

public interface EpisodeService {
    List<EpisodeDTO> getAllEpisodes();
    EpisodeDTO saveEpisode(EpisodeDTO episodeDTO);
}

