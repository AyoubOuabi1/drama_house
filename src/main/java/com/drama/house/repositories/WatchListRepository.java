package com.drama.house.repositories;

import com.drama.house.entities.Episode;
import com.drama.house.entities.Movie;
import com.drama.house.entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WatchListRepository extends JpaRepository<Watchlist,Long> {
    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM Watchlist w JOIN w.movies m WHERE w.user.id = :userId AND m.id = :movieId")
    boolean existsMovieInWatchlist(@Param("userId") Long userId, @Param("movieId") Long movieId);

    List<Watchlist> findAllByMoviesContains(Movie movie);

}
