package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

}

