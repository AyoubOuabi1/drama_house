package com.drama.house.repositories;

import com.drama.house.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Custom query methods if needed

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:name%")
    List<Movie> findMovieByName(String name);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.name = :name")
    List<Movie> findMovieByGenre(String name);

}
