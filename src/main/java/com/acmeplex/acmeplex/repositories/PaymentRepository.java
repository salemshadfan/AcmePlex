package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTicketId(Long ticketId);
    List<Payment> findByPaymentMethod(String paymentMethod);
    List<Payment> findByAmountGreaterThan(double amount);

    @Query("SELECT SUM(p.amount) FROM Payment p")
    Double calculateTotalRevenue();

    long countByPaymentMethod(String paymentMethod);

    @Query("SELECT p FROM Payment p JOIN p.ticket t WHERE t.customer.id = :customerId")
    List<Payment> findPaymentsByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT p FROM Payment p WHERE p.ticket.id BETWEEN :startId AND :endId")
    List<Payment> findPaymentsByTicketIdRange(@Param("startId") Long startId, @Param("endId") Long endId);

    List<Payment> findByAmountBetween(double minAmount, double maxAmount);

    void deleteByTicketId(Long ticketId);
}
