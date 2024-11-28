package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.Seat;
import com.acmeplex.acmeplex.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Long> {

}

