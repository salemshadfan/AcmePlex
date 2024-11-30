package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Receipt;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Transactional
    void deleteByTicketId(Long id);

}

