package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Movie;
import com.acmeplex.acmeplex.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface MovieRepository extends JpaRepository<Movie, Long> {

}
