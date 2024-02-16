package com.drama.house.services;

import com.drama.house.dtos.SeasonDTO;

import java.util.List;

public interface SeasonService {
    List<SeasonDTO> getAllSeasons();
    SeasonDTO saveSeason(SeasonDTO seasonDTO);
}

