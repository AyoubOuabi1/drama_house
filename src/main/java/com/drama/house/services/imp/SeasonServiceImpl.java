package com.drama.house.services.imp;

import com.drama.house.dtos.SeasonDTO;
import com.drama.house.entities.Season;
import com.drama.house.repositories.SeasonRepository;
import com.drama.house.services.SeasonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {
    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SeasonDTO> getAllSeasons() {
        List<Season> seasons = seasonRepository.findAll();
        return seasons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SeasonDTO saveSeason(SeasonDTO seasonDTO) {
        Season season = convertToEntity(seasonDTO);
        season = seasonRepository.save(season);
        return convertToDTO(season);
    }

    private SeasonDTO convertToDTO(Season season) {
        return modelMapper.map(season, SeasonDTO.class);
    }

    private Season convertToEntity(SeasonDTO seasonDTO) {
        return modelMapper.map(seasonDTO, Season.class);
    }
}

