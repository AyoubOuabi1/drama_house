package com.drama.house.mappers;

import com.drama.house.dtos.requests.RequestSeasonDTO;
import com.drama.house.entities.Season;

public class SeasonMapper {
    public static Season toSeason(RequestSeasonDTO requestSeasonDTO) {
        Season season = new Season();
        season.setTitle(requestSeasonDTO.getTitle());
        season.setNumberOfEpisodes(requestSeasonDTO.getNumberOfEpisodes());
        season.setReleaseDate(requestSeasonDTO.getReleaseDate());
        return season;
    }
}
