package com.drama.house.repositories;

import com.drama.house.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    // Add custom query methods if needed
}
