package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Seat;
import com.acmeplex.acmeplex.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByMovieId(Long movieId);
    List<ShowTime> findByTheatreId(Long theatreId);
    List<ShowTime> findByDate(String date);
    List<ShowTime> findByMovieIdAndDate(Long movieId, String date);
    List<ShowTime> findByTheatreIdAndDate(Long theatreId, String date);
    List<ShowTime> findByMovieIdAndTheatreIdAndDate(Long movieId, Long theatreId, String date);

    @Query("SELECT s FROM ShowTime s WHERE s.movie.id = :movieId AND s.date >= :date")
    List<ShowTime> findUpcomingShowTimesForMovie(@Param("movieId") Long movieId, @Param("date") String date);

    @Query("SELECT s FROM Seat s WHERE s.showTime.id = :showTimeId AND s.isAvailable = true")
    List<Seat> findAvailableSeatsForShowTime(@Param("showTimeId") Long showTimeId);

    long countByMovieId(Long movieId);
    void deleteByMovieId(Long movieId);
}

