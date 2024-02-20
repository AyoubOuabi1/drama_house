package com.drama.house.services;

import com.drama.house.dtos.SeasonDTO;
import com.drama.house.dtos.requests.RequestSeasonDTO;

import java.util.List;

public interface SeasonService {
    List<SeasonDTO> getAllSeasons();
    SeasonDTO saveSeason(RequestSeasonDTO seasonDTO);

    SeasonDTO getSeasonById(Long seasonId);
}

