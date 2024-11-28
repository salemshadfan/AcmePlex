package com.acmeplex.acmeplex;

import com.acmeplex.acmeplex.entities.Movie;
import com.acmeplex.acmeplex.entities.ShowTime;
import com.acmeplex.acmeplex.entities.Theatre;
import com.acmeplex.acmeplex.repositories.MovieRepository;
import com.acmeplex.acmeplex.repositories.TheatreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @PostConstruct
    public void init() {
        // Clear existing data (optional)
        movieRepository.deleteAll();
        theatreRepository.deleteAll();

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
        showTime1.setDate("2024-12-01");
        showTime1.setStartTime("19:30:00");
        showTime1.setMovie(movie1);
        showTime1.setTheatre(theatre1);

        ShowTime showTime2 = new ShowTime();
        showTime2.setDate("2024-12-01");
        showTime2.setStartTime("21:00:00");
        showTime2.setMovie(movie1);
        showTime2.setTheatre(theatre2);

        // Add ShowTimes for Movie 2
        ShowTime showTime3 = new ShowTime();
        showTime3.setDate("2024-12-02");
        showTime3.setStartTime("20:00:00");
        showTime3.setMovie(movie2);
        showTime3.setTheatre(theatre1);

        ShowTime showTime4 = new ShowTime();
        showTime4.setDate("2024-12-02");
        showTime4.setStartTime("22:00:00");
        showTime4.setMovie(movie2);
        showTime4.setTheatre(theatre2);

        // Link ShowTimes to Movies
        movie1.setShowTimes(List.of(showTime1, showTime2));
        movie2.setShowTimes(List.of(showTime3, showTime4));

        // Save Movies (ShowTimes will cascade and save automatically)
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        System.out.println("Movies, theatres, and showtimes initialized successfully.");
    }
}
