package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    List<RegisteredUser> findAll();
    List<RegisteredUser> findByRegistrationDate(LocalDate registrationDate);
    List<RegisteredUser> findByRegistrationDateAfter(LocalDate date);
    List<RegisteredUser> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);

    long count();

    @Query("SELECT COUNT(r) * r.annualFee FROM RegisteredUser r")
    Double calculateTotalAnnualFees();

    List<RegisteredUser> findByName(String name);
    List<RegisteredUser> findByAnnualFeeGreaterThan(double fee);

    void deleteById(Long id);

    @Query("SELECT DISTINCT r FROM RegisteredUser r JOIN r.tickets t WHERE t.price > :price")
    List<RegisteredUser> findRegisteredUsersWithTicketsAbovePrice(@Param("price") double price);
}

