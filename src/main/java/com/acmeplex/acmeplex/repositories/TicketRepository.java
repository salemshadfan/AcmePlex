package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomerId(Long customerId);
    List<Ticket> findByShowTimeId(Long showTimeId);
    Optional<Ticket> findBySeatId(Long seatId);
    List<Ticket> findByCustomerIdAndShowTimeId(Long customerId, Long showTimeId);
    List<Ticket> findByPriceBetween(double minPrice, double maxPrice);

    long countByShowTimeId(Long showTimeId);

    @Query("SELECT SUM(t.price) FROM Ticket t WHERE t.showTime.id = :showTimeId")
    Double calculateTotalRevenueForShowTime(@Param("showTimeId") Long showTimeId);

    @Query("SELECT t FROM Ticket t JOIN t.showTime s WHERE s.theatre.id = :theatreId")
    List<Ticket> findTicketsByTheatreId(@Param("theatreId") Long theatreId);

    @Query("SELECT t FROM Ticket t JOIN t.showTime s WHERE s.movie.id = :movieId")
    List<Ticket> findTicketsByMovieId(@Param("movieId") Long movieId);

    void deleteByShowTimeId(Long showTimeId);
}

