package com.drama.house.services.imp;

import com.drama.house.dtos.SeriesDTO;
import com.drama.house.entities.Series;
import com.drama.house.repositories.SeriesRepository;
import com.drama.house.services.SeriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SeriesDTO> getAllSeries() {
        List<Series> seriesList = seriesRepository.findAll();
        return seriesList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeriesDTO saveSeries(SeriesDTO seriesDTO) {
        Series series = convertToEntity(seriesDTO);
        series = seriesRepository.save(series);
        return convertToDTO(series);
    }

    private SeriesDTO convertToDTO(Series series) {
        return modelMapper.map(series, SeriesDTO.class);
    }

    private Series convertToEntity(SeriesDTO seriesDTO) {
        return modelMapper.map(seriesDTO, Series.class);
    }
}

