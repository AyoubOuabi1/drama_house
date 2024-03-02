package com.drama.house.repositories;

import com.drama.house.dtos.UserDTO;
import com.drama.house.entities.Episode;
import com.drama.house.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
