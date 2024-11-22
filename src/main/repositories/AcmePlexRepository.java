package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.AcmePlex;
import com.acmeplex.acmeplex.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository
public interface AcmePlexRepository extends JpaRepository<AcmePlex, Long> {
    // Custom query to fetch theatres associated with a specific AcmePlex
    @Query("SELECT a.theatres FROM AcmePlex a WHERE a.id = :acmePlexId")
    List<Theatre> findTheatresByAcmePlexId(@Param("acmePlexId") Long acmePlexId);
}
