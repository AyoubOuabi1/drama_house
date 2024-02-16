package com.drama.house.repositories;

import com.drama.house.entities.Episode;
import com.drama.house.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
