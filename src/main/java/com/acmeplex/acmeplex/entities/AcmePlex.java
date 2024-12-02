package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class AcmePlex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "acmeplex", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Theatre> theatres;

    public AcmePlex() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Theatre> getTheatres() { return theatres; }
    public void setTheatres(List<Theatre> theatres) { this.theatres = theatres; }
}
