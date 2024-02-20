package com.drama.house.services.imp;

import com.drama.house.dtos.SeasonDTO;
import com.drama.house.dtos.requests.RequestSeasonDTO;
import com.drama.house.dtos.requests.RequestSeriesDTO;
import com.drama.house.entities.Season;
import com.drama.house.entities.Series;
import com.drama.house.mappers.SeasonMapper;
import com.drama.house.repositories.SeasonRepository;
import com.drama.house.services.S3Service;
import com.drama.house.services.SeasonService;
import com.drama.house.services.SeriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepository seasonRepository;

    private final ModelMapper modelMapper;

    private final S3Service s3Service;
    private final SeriesService seriesService;
    @Autowired
    public SeasonServiceImpl(SeasonRepository seasonRepository, ModelMapper modelMapper,
                             S3Service s3Service, SeriesService seriesService) {
        this.seasonRepository = seasonRepository;
        this.modelMapper = modelMapper;
        this.s3Service = s3Service;
        this.seriesService = seriesService;
    }

    @Override
    public List<SeasonDTO> getAllSeasons() {
        List<Season> seasons = seasonRepository.findAll();
        return seasons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeasonDTO saveSeason(RequestSeasonDTO seasonDTO) {
        Season season = convertToEntity(seasonDTO);
        season = seasonRepository.save(season);
        return convertToDTO(season);
    }

    @Override
    public SeasonDTO getSeasonById(Long seasonId) {
        return convertToDTO(seasonRepository.findById(seasonId).orElse(null));
    }

    private SeasonDTO convertToDTO(Season season) {
        SeasonDTO seasonDTO = modelMapper.map(season, SeasonDTO.class);
        seasonDTO.setSeriesName(season.getSeries().getTitle());
        return seasonDTO;
    }

    private Season convertToEntity(RequestSeasonDTO seasonDTO) {
        Season season = SeasonMapper.toSeason(seasonDTO);
        Series series = modelMapper.map(seriesService.findById(seasonDTO.getSeriesId()), Series.class);
        season.setSeries(series);
        season.setCoverUrl(s3Service.uploadFile("seasons_images",seasonDTO.getCoverFile()));
        season.setPosterUrl(s3Service.uploadFile("seasons_images",seasonDTO.getPosterFile()));
        return season;
    }
}

