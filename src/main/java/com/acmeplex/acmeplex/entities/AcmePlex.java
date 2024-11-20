package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AcmePlex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "acmeplex")
    private List<Theatre> theatres;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Theatre> getTheatres() { return theatres; }
    public void setTheatres(List<Theatre> theatres) { this.theatres = theatres; }
}
