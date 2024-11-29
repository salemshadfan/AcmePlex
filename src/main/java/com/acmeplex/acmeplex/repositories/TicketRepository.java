package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
   
}

