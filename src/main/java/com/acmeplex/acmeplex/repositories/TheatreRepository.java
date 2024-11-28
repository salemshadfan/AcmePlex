package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}

