package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShowTimeId(Long showTimeId);
    List<Seat> findByShowTimeIdAndIsAvailableTrue(Long showTimeId);

    long countByShowTimeId(Long showTimeId);
    long countByShowTimeIdAndIsAvailableTrue(Long showTimeId);

    Optional<Seat> findByShowTimeIdAndRowNumberAndSeatNumber(Long showTimeId, int rowNumber, int seatNumber);
    List<Seat> findByShowTimeIdAndRowNumber(Long showTimeId, int rowNumber);

    @Modifying
    @Query("UPDATE Seat s SET s.isAvailable = false WHERE s.id = :seatId")
    void markSeatAsUnavailable(@Param("seatId") Long seatId);

    List<Seat> findByIsAvailable(boolean isAvailable);
    void deleteByShowTimeId(Long showTimeId);
}

