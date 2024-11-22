package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Customer;
import com.acmeplex.acmeplex.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Customer> findByName(String name);

    @Query("SELECT c.tickets FROM Customer c WHERE c.id = :customerId")
    List<Ticket> findTicketsByCustomerId(@Param("customerId") Long customerId);

    Optional<Customer> findByEmailAndPassword(String email, String password);
    void deleteById(Long id);

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    List<Customer> findByNameContaining(@Param("name") String name);

    long count();
}
