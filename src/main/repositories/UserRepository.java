package com.acmeplex.acmeplex.repositories;

import com.acmeplex.acmeplex.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    List<User> findByName(String name);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findByNameContaining(@Param("name") String name);

    void deleteByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u JOIN u.tickets t WHERE t.price > :price")
    List<User> findUsersWithTicketsAbovePrice(@Param("price") double price);

    @Query("SELECT DISTINCT u FROM User u JOIN u.tickets t JOIN t.showTime s WHERE s.movie.id = :movieId")
    List<User> findUsersByMovieId(@Param("movieId") Long movieId);

    long count();

    @Query("SELECT DISTINCT u FROM User u JOIN u.tickets t WHERE t.showTime.id = :showTimeId")
    List<User> findUsersByShowTimeId(@Param("showTimeId") Long showTimeId);
}

