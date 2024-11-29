package com.acmeplex.acmeplex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acmeplex.acmeplex.repositories.ShowTimeRepository;
import com.acmeplex.acmeplex.entities.Seat;

@RestController
@RequestMapping("/api/showtimes")
public class ShowTimeController {

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @GetMapping("/{id}/seats")
    public ResponseEntity<List<Seat>> getSeatMap(@PathVariable Long id) {
        return showTimeRepository.findById(id)
            .map(showTime -> ResponseEntity.ok(showTime.getSeats()))
            .orElse(ResponseEntity.notFound().build());
    }
}
