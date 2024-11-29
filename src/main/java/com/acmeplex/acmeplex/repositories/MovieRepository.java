package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Long> {

}
