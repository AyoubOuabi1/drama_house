package com.drama.house.controllers;

import com.drama.house.dtos.SeriesDTO;
import com.drama.house.dtos.requests.RequestSeriesDTO;
import com.drama.house.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/series")
public class SeriesController {
    private SeriesService seriesService;
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }
    @GetMapping
    public List<SeriesDTO> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @PostMapping
    public SeriesDTO addSeries(@ModelAttribute RequestSeriesDTO seriesDTO) {
        return seriesService.saveSeries(seriesDTO);
    }
}

