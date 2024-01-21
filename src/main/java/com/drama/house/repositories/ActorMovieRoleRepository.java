package com.drama.house.repositories;

import com.drama.house.entities.ActorMovieRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorMovieRoleRepository extends JpaRepository<ActorMovieRole, Long> {
    // Add custom query methods if needed
}

