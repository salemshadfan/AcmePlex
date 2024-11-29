package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.AcmePlex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AcmePlexRepository extends JpaRepository<AcmePlex, Long> {
    
}
