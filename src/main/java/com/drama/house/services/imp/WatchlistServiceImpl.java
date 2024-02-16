package com.drama.house.services.imp;

import com.drama.house.dtos.WatchlistDTO;
import com.drama.house.entities.Watchlist;
import com.drama.house.repositories.WatchListRepository;
import com.drama.house.services.WatchlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistServiceImpl implements WatchlistService {
    @Autowired
    private WatchListRepository watchlistRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<WatchlistDTO> getAllWatchlists() {
        List<Watchlist> watchlists = watchlistRepository.findAll();
        return watchlists.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WatchlistDTO saveWatchlist(WatchlistDTO watchlistDTO) {
        Watchlist watchlist = convertToEntity(watchlistDTO);
        watchlist = watchlistRepository.save(watchlist);
        return convertToDTO(watchlist);
    }

    private WatchlistDTO convertToDTO(Watchlist watchlist) {
        return modelMapper.map(watchlist, WatchlistDTO.class);
    }

    private Watchlist convertToEntity(WatchlistDTO watchlistDTO) {
        return modelMapper.map(watchlistDTO, Watchlist.class);
    }
}

