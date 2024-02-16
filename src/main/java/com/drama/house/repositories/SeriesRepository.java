package com.drama.house.repositories;

import com.drama.house.entities.Episode;
import com.drama.house.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series,Long> {
}
