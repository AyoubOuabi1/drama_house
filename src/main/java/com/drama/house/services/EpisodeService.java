package com.drama.house.services;

import com.drama.house.dto.EpisodeDTO;

import java.util.List;

public interface EpisodeService {
    EpisodeDTO getEpisodeById(Long episodeId);
    List<EpisodeDTO> getAllEpisodes();
    EpisodeDTO createEpisode(EpisodeDTO episodeDTO);
    EpisodeDTO updateEpisode(Long episodeId, EpisodeDTO episodeDTO);
    void deleteEpisode(Long episodeId);
}
