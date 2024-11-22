package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    Optional<Theatre> findByName(String name);
    List<Theatre> findByLocation(String location);
    List<Theatre> findByAcmeplexId(Long acmeplexId);

    long countByAcmeplexId(Long acmeplexId);

    @Query("SELECT DISTINCT t FROM Theatre t JOIN t.showTimes s WHERE s.date = :date")
    List<Theatre> findTheatresWithShowTimesOnDate(@Param("date") String date);

    @Query("SELECT DISTINCT t FROM Theatre t JOIN t.showTimes s WHERE s.movie.id = :movieId")
    List<Theatre> findTheatresWithShowTimesForMovie(@Param("movieId") Long movieId);

    void deleteByName(String name);

    @Query("SELECT DISTINCT t FROM Theatre t JOIN t.showTimes s WHERE s.date BETWEEN :startDate AND :endDate")
    List<Theatre> findTheatresWithShowTimesBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Theatre> findByLocationAndAcmeplexId(String location, Long acmeplexId);
}

