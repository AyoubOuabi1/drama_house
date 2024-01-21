package com.drama.house.repositories;

import com.drama.house.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Add custom query methods if needed
}

