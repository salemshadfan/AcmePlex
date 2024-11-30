package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomerId(Long customerId);
    Optional<Ticket> findById(Long ticketId);
}

