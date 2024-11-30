package com.acmeplex.acmeplex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acmeplex.acmeplex.entities.Movie;
import com.acmeplex.acmeplex.repositories.MovieRepository;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/api/movies/search")
    public List<Movie> searchMovies(@RequestParam String query) {

        return movieRepository.findByTitleContainingIgnoreCase(query);
    }
}
