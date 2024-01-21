package com.drama.house.repositories;

import com.drama.house.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    // Add custom query methods if needed
}

