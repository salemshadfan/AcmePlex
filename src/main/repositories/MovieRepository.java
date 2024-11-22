package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Movie;
import com.acmeplex.acmeplex.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findByGenre(String genre);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
    List<Movie> findByTitleContaining(@Param("title") String title);

    List<Movie> findByDurationLessThan(int duration);

    @Query("SELECT m FROM Movie m JOIN m.showTimes st WHERE st.id = :showTimeId")
    List<Movie> findMoviesByShowTimeId(@Param("showTimeId") Long showTimeId);

    @Query("SELECT m.showTimes FROM Movie m WHERE m.id = :movieId")
    List<ShowTime> findShowTimesByMovieId(@Param("movieId") Long movieId);

    long countByGenre(String genre);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.showTimes st WHERE st.date BETWEEN :startDate AND :endDate")
    List<Movie> findMoviesWithShowTimesBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
