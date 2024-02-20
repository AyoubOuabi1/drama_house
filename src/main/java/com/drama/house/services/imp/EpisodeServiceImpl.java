package com.drama.house.services.imp;

import com.drama.house.dtos.EpisodeDTO;
import com.drama.house.dtos.requests.RequestEpisodeDTO;
import com.drama.house.entities.Episode;
import com.drama.house.entities.Season;
import com.drama.house.mappers.EpisodeMapper;
import com.drama.house.repositories.EpisodeRepository;
import com.drama.house.services.EpisodeService;
import com.drama.house.services.S3Service;
import com.drama.house.services.SeasonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepository;

    private final ModelMapper modelMapper;

    private final S3Service s3Service;

    private final SeasonService seasonService;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository, ModelMapper modelMapper,
                              S3Service s3Service, SeasonService seasonService) {
        this.episodeRepository = episodeRepository;
        this.modelMapper = modelMapper;
        this.s3Service = s3Service;
        this.seasonService = seasonService;
    }
    @Override
    public List<EpisodeDTO> getAllEpisodes() {
        List<Episode> episodes = episodeRepository.findAll();
        return episodes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EpisodeDTO saveEpisode(RequestEpisodeDTO episodeDTO) {
        Episode episode = convertToEntity(episodeDTO);
        episode = episodeRepository.save(episode);
        return convertToDTO(episode);
    }

    private EpisodeDTO convertToDTO(Episode episode) {
        EpisodeDTO episodeDTO = modelMapper.map(episode, EpisodeDTO.class);
        episodeDTO.setSeason(episode.getSeason().getTitle());
        episodeDTO.setSeries(episode.getSeason().getSeries().getTitle());
        return episodeDTO;
    }

    private Episode convertToEntity(RequestEpisodeDTO episodeDTO) {
        Episode episode = EpisodeMapper.toEpisode(episodeDTO);
        episode.setSeason(modelMapper.map(seasonService.getSeasonById(episodeDTO.getSeasonId()), Season.class));
        episode.setVideoUrl(s3Service.uploadFile("episode_videos",episodeDTO.getVideoFile()));
        episode.setPosterUrl(s3Service.uploadFile("episodes_images",episodeDTO.getPosterFile()));
        return episode;
    }
}
