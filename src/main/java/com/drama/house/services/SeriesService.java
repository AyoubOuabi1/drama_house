package com.drama.house.services;

import com.drama.house.dtos.SeriesDTO;

import java.util.List;

public interface SeriesService {
    List<SeriesDTO> getAllSeries();
    SeriesDTO saveSeries(SeriesDTO seriesDTO);
}

