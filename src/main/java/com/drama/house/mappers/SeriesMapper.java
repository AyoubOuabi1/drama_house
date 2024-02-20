package com.drama.house.mappers;

import com.drama.house.dtos.requests.RequestSeriesDTO;
import com.drama.house.entities.Series;

public class SeriesMapper {
    public static Series toSeries(RequestSeriesDTO requestSeriesDTO) {
        Series series = new Series();
        series.setTitle(requestSeriesDTO.getTitle());
        series.setDescription(requestSeriesDTO.getDescription());
        series.setReleaseDate(requestSeriesDTO.getReleaseDate());
        series.setNumberOfSeasons(requestSeriesDTO.getNumberOfSeasons());
        series.setLanguage(requestSeriesDTO.getLanguage());
        series.setTrailerUrl(requestSeriesDTO.getTrailerUrl());
        return series;
    }
}
