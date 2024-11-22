package com.acmeplex.acmeplex.controllers;

import com.acmeplex.acmeplex.entities.Movie;
import com.acmeplex.acmeplex.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;


    @GetMapping(value = {"/", "/{id}"})
    public ResponseEntity<?> getMovies(@PathVariable(required = false) Long id) {
        if (id == null) {

            List<Movie> movies = movieRepository.findAll();
            return ResponseEntity.ok(movies);
        } else {

            return movieRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }
}
