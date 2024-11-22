package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

}

