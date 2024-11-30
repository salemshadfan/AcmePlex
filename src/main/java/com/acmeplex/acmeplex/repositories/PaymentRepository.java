package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Payment;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Transactional
    void deleteByTicketId(Long ticketId);
}
