package com.drama.house.services;

import com.drama.house.dtos.SeriesDTO;
import com.drama.house.dtos.requests.RequestSeriesDTO;

import java.util.List;

public interface SeriesService {
    List<SeriesDTO> getAllSeries();
    SeriesDTO saveSeries(RequestSeriesDTO seriesDTO);
    SeriesDTO findById(Long id);
}

