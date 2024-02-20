package com.drama.house.mappers;

import com.drama.house.dtos.requests.RequestEpisodeDTO;
import com.drama.house.entities.Episode;

public class EpisodeMapper {

    public static Episode toEpisode(RequestEpisodeDTO requestEpisodeDTO) {
        Episode episode = new Episode();
        episode.setEpisodeNumber(requestEpisodeDTO.getEpisodeNumber());
        episode.setTitle(requestEpisodeDTO.getTitle());
        episode.setDescription(requestEpisodeDTO.getDescription());
        episode.setDuration(requestEpisodeDTO.getDuration());
        episode.setReleaseDate(requestEpisodeDTO.getReleaseDate());
        return episode;
    }
}
