package com.drama.house.services.imp;

import com.drama.house.dtos.EpisodeDTO;
import com.drama.house.entities.Episode;
import com.drama.house.repositories.EpisodeRepository;
import com.drama.house.services.EpisodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EpisodeDTO> getAllEpisodes() {
        List<Episode> episodes = episodeRepository.findAll();
        return episodes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EpisodeDTO saveEpisode(EpisodeDTO episodeDTO) {
        Episode episode = convertToEntity(episodeDTO);
        episode = episodeRepository.save(episode);
        return convertToDTO(episode);
    }

    private EpisodeDTO convertToDTO(Episode episode) {
        return modelMapper.map(episode, EpisodeDTO.class);
    }

    private Episode convertToEntity(EpisodeDTO episodeDTO) {
        return modelMapper.map(episodeDTO, Episode.class);
    }
}
