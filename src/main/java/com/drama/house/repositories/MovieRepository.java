package com.drama.house.repositories;

import com.drama.house.entities.Movie;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Custom query methods if needed

    @Query("SELECT m FROM Movie m")
    Page<Movie> findAllMovies(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:name%")
    Page<Movie> findMovieByName(String name,Pageable pageable);

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.name = :name")
    Page<Movie> findMovieByGenre(String name,Pageable pageable);

    @Query("SELECT m FROM Movie m ORDER BY m.id DESC")
    Page<Movie> findLastTenMoviesAdded(Pageable pageable);

}
