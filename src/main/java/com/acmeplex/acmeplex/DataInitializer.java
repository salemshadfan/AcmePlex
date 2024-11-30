package com.acmeplex.acmeplex;

import com.acmeplex.acmeplex.entities.Movie;
import com.acmeplex.acmeplex.entities.Seat;
import com.acmeplex.acmeplex.entities.ShowTime;
import com.acmeplex.acmeplex.entities.Theatre;
import com.acmeplex.acmeplex.repositories.MovieRepository;
import com.acmeplex.acmeplex.repositories.SeatRepository;
import com.acmeplex.acmeplex.repositories.TheatreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private SeatRepository SeatRepository;

    @PostConstruct
public void init() {
    jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
    jdbcTemplate.execute("TRUNCATE TABLE show_time;");
    jdbcTemplate.execute("TRUNCATE TABLE movie;");
    jdbcTemplate.execute("TRUNCATE TABLE theatre;");
    jdbcTemplate.execute("TRUNCATE TABLE seat;");
    jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
    movieRepository.deleteAll();
    theatreRepository.deleteAll();
    SeatRepository.deleteAll();

    // Create Theatre objects
    Theatre theatre1 = new Theatre();
    theatre1.setName("Theatre 1");
    theatre1.setLocation("Downtown");

    Theatre theatre2 = new Theatre();
    theatre2.setName("Theatre 2");
    theatre2.setLocation("Uptown");

    theatreRepository.save(theatre1);
    theatreRepository.save(theatre2);

    // Create Movie objects
    Movie movie1 = new Movie();
    movie1.setTitle("Moana 2");
    movie1.setDescription("Walt Disney Animation Studios’ epic animated musical...");
    movie1.setDuration(100);
    movie1.setGenre("Animated");
    movie1.setImageUrl("https://filmdb.landmarkcinemas.com/FilmImages/22/1/125115/Moana2OfficialPoster.jpg");

    Movie movie2 = new Movie();
    movie2.setTitle("Wicked");
    movie2.setDescription("Wicked, the untold story of the witches of Oz...");
    movie2.setDuration(160);
    movie2.setGenre("Fantasy");
    movie2.setImageUrl("https://filmdb.landmarkcinemas.com/FilmImages/22/1/125006/Web-PosterFrame-MLC-Wicked.jpg");
    // Add ShowTimes for Movie 1
    ShowTime showTime1 = new ShowTime();
    showTime1.setDate(LocalDate.of(2024, 12, 1)); // Use LocalDate.of() for the date
    showTime1.setStartTime(LocalTime.of(19, 30)); // Use LocalTime.of() for the time
    showTime1.setMovie(movie1);
    showTime1.setTheatre(theatre1);

    ShowTime showTime2 = new ShowTime();
    showTime2.setDate(LocalDate.of(2024, 12, 1)); // Use LocalDate.of() for the date
    showTime2.setStartTime(LocalTime.of(21, 0)); // Use LocalTime.of() for the time
    showTime2.setMovie(movie1);
    showTime2.setTheatre(theatre2);

    // Add ShowTimes for Movie 2
    ShowTime showTime3 = new ShowTime();
    showTime3.setDate(LocalDate.of(2024, 12, 2)); // Use LocalDate.of() for the date
    showTime3.setStartTime(LocalTime.of(20, 0)); // Use LocalTime.of() for the time
    showTime3.setMovie(movie2);
    showTime3.setTheatre(theatre1);

    ShowTime showTime4 = new ShowTime();
    showTime4.setDate(LocalDate.of(2024, 12, 2)); // Use LocalDate.of() for the date
    showTime4.setStartTime(LocalTime.of(22, 0)); // Use LocalTime.of() for the time
    showTime4.setMovie(movie2);
    showTime4.setTheatre(theatre2);


    // Link ShowTimes to Movies
    movie1.setShowTimes(List.of(showTime1, showTime2));
    movie2.setShowTimes(List.of(showTime3, showTime4));

    // Save Movies (ShowTimes will cascade and save automatically)
    movieRepository.save(movie1);
    movieRepository.save(movie2);

    // Populate Seats for Each ShowTime
    populateSeatsForShowTime(showTime1);
    populateSeatsForShowTime(showTime2);
    populateSeatsForShowTime(showTime3);
    populateSeatsForShowTime(showTime4);

    System.out.println("Movies, theatres, showtimes, and seats initialized successfully.");
}

private void populateSeatsForShowTime(ShowTime showTime) {
    List<Seat> seats = new ArrayList<>();
    for (int row = 1; row <= 5; row++) { // Example: 5 rows
        for (int seatNumber = 1; seatNumber <= 10; seatNumber++) { // Example: 10 seats per row
            Seat seat = new Seat();
            seat.setRowNumber(row);
            seat.setSeat_Number(seatNumber);
            seat.setAvailable(true);
            seat.setShowTime(showTime); // Link the seat to the ShowTime
            seats.add(seat);
        }
    }

    // Save all seats for the ShowTime
    SeatRepository.saveAll(seats);

    // Link seats to the ShowTime
    showTime.setSeats(seats);
    }   
}