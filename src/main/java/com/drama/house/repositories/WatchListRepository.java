package com.drama.house.repositories;

import com.drama.house.entities.Episode;
import com.drama.house.entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<Watchlist,Long> {
}
