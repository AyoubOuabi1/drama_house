package com.drama.house.services;

import com.drama.house.dtos.EpisodeDTO;
import com.drama.house.dtos.requests.RequestEpisodeDTO;

import java.util.List;

public interface EpisodeService {
    List<EpisodeDTO> getAllEpisodes();
    EpisodeDTO saveEpisode(RequestEpisodeDTO episodeDTO);
}

