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

        List<Movie> movies = new ArrayList<>();
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

        Movie movie3 = new Movie();
        movie3.setTitle("Hello, Love, Again");
        movie3.setDescription("A touching story of long-lost lovers finding each other...");
        movie3.setDuration(125);
        movie3.setGenre("Drama");
        movie3.setImageUrl("https://filmdb.landmarkcinemas.com/FilmImages/22/1/125592/HelloLoveAgain-OfficialPoster.gif");

        Movie movie4 = new Movie();
        movie4.setTitle("Red One");
        movie4.setDescription("A Christmas action movie featuring Dwayne Johnson...");
        movie4.setDuration(123);
        movie4.setGenre("Action");
        movie4.setImageUrl("https://filmdb.landmarkcinemas.com/FilmImages/22/1/125357/RedOnePoster.jpg");

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
        showTime4.setDate(LocalDate.of(2024, 12, 5)); // Use LocalDate.of() for the date
        showTime4.setStartTime(LocalTime.of(22, 0)); // Use LocalTime.of() for the time
        showTime4.setMovie(movie2);
        showTime4.setTheatre(theatre2);

        ShowTime showTime5 = new ShowTime();
        showTime5.setDate(LocalDate.of(2024, 12, 4)); // Use LocalDate.of() for the date
        showTime5.setStartTime(LocalTime.of(20, 0)); // Use LocalTime.of() for the time
        showTime5.setMovie(movie3);
        showTime5.setTheatre(theatre1);

        ShowTime showTime6 = new ShowTime();
        showTime6.setDate(LocalDate.of(2024, 12, 4)); // Use LocalDate.of() for the date
        showTime6.setStartTime(LocalTime.of(22, 0)); // Use LocalTime.of() for the time
        showTime6.setMovie(movie3);
        showTime6.setTheatre(theatre2);


        ShowTime showTime7 = new ShowTime();
        showTime7.setDate(LocalDate.of(2024, 12, 4)); // Use LocalDate.of() for the date
        showTime7.setStartTime(LocalTime.of(20, 0)); // Use LocalTime.of() for the time
        showTime7.setMovie(movie4);
        showTime7.setTheatre(theatre1);

        ShowTime showTime8 = new ShowTime();
        showTime8.setDate(LocalDate.of(2024, 12, 5)); // Use LocalDate.of() for the date
        showTime8.setStartTime(LocalTime.of(22, 0)); // Use LocalTime.of() for the time
        showTime8.setMovie(movie4);
        showTime8.setTheatre(theatre2);

        ShowTime showTime9 = new ShowTime();
        showTime9.setDate(LocalDate.of(2024, 12, 7)); // Use LocalDate.of() for the date
        showTime9.setStartTime(LocalTime.of(22, 0)); // Use LocalTime.of() for the time
        showTime9.setMovie(movie3);
        showTime9.setTheatre(theatre2);

        ShowTime showTime10 = new ShowTime();
        showTime10.setDate(LocalDate.of(2024, 12, 8)); // Use LocalDate.of() for the date
        showTime10.setStartTime(LocalTime.of(22, 0)); // Use LocalTime.of() for the time
        showTime10.setMovie(movie1);
        showTime10.setTheatre(theatre2);


        // Link ShowTimes to Movies
        movie1.setShowTimes(List.of(showTime1, showTime2,showTime10));
        movie2.setShowTimes(List.of(showTime3, showTime4));
        movie3.setShowTimes(List.of(showTime5, showTime6, showTime9));
        movie4.setShowTimes(List.of(showTime7, showTime8));

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);

        movieRepository.saveAll(movies);

        populateSeatsForShowTimes(List.of(showTime1,showTime2,showTime3,showTime4,showTime5,showTime6,showTime9,showTime8, showTime7, showTime10));




        System.out.println("Movies, theatres, showtimes, and seats initialized successfully.");
    }


    private void populateSeatsForShowTimes(List<ShowTime> showTimes) {
        List<Seat> allSeats = new ArrayList<>();
        for (ShowTime showTime : showTimes) {
            List<Seat> seats = new ArrayList<>();
            for (int row = 1; row <= 5; row++) {
                for (int seatNumber = 1; seatNumber <= 10; seatNumber++) {
                    Seat seat = new Seat();
                    seat.setRowNumber(row);
                    seat.setSeat_Number(seatNumber);
                    seat.setAvailable(true);
                    seat.setShowTime(showTime);
                    seats.add(seat);
                }
            }
            allSeats.addAll(seats);
            showTime.setSeats(seats);
        }
        SeatRepository.saveAll(allSeats);
    }
}