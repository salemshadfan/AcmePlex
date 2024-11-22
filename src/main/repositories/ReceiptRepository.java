package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Optional<Receipt> findByTicketId(Long ticketId);
    List<Receipt> findByReceiptDate(String receiptDate);
    List<Receipt> findByTotalAmountGreaterThan(double totalAmount);

    @Query("SELECT SUM(r.totalAmount) FROM Receipt r")
    Double calculateTotalRevenue();

    @Query("SELECT r FROM Receipt r JOIN r.ticket t WHERE t.customer.id = :customerId")
    List<Receipt> findReceiptsByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT r FROM Receipt r WHERE r.receiptDate BETWEEN :startDate AND :endDate")
    List<Receipt> findReceiptsBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT r FROM Receipt r WHERE r.ticket.id BETWEEN :startId AND :endId")
    List<Receipt> findReceiptsByTicketIdRange(@Param("startId") Long startId, @Param("endId") Long endId);

    List<Receipt> findByTotalAmountBetween(double minAmount, double maxAmount);

    void deleteByTicketId(Long ticketId);
}

