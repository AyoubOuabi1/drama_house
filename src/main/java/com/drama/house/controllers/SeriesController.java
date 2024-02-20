package com.drama.house.controllers;

import com.drama.house.dtos.SeriesDTO;
import com.drama.house.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    @GetMapping
    public List<SeriesDTO> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @PostMapping
    public SeriesDTO addSeries(@RequestBody SeriesDTO seriesDTO) {
        return seriesService.saveSeries(seriesDTO);
    }
}
