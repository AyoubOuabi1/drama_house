package com.drama.house.repositories;

import com.drama.house.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Add custom query methods if needed
}

