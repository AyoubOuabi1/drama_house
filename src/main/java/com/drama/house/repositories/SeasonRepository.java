package com.drama.house.repositories;

import com.drama.house.entities.Episode;
import com.drama.house.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season,Long> {
}
