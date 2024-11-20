package com.acmeplex.acmeplex.entities;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private int duration; // Duration in minutes
    private String description;

    @OneToMany(mappedBy = "movie")
    private List<ShowTime> showTimes;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<ShowTime> getShowTimes() { return showTimes; }
    public void setShowTimes(List<ShowTime> showTimes) { this.showTimes = showTimes; }
}
