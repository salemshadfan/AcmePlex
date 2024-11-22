package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Customer;
import com.acmeplex.acmeplex.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT r FROM RegisteredUser r WHERE r.email = ?1")
    Optional<RegisteredUser> findRegisteredUserByEmail(String email);

    @Query("SELECT r FROM RegisteredUser r WHERE r.email = ?1 AND r.password = ?2")
    Optional<RegisteredUser> findRegisteredUserByEmailAndPassword(String email, String password);
}
