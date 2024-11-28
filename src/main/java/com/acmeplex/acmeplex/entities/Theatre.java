package com.acmeplex.acmeplex.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;


    @ManyToOne
    @JoinColumn(name = "acmeplex_id")
    private AcmePlex acmeplex;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ShowTime> showTimes;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public AcmePlex getAcmeplex() { return acmeplex; }
    public void setAcmeplex(AcmePlex acmeplex) { this.acmeplex = acmeplex; }
    public List<ShowTime> getShowTimes() { return showTimes; }
    public void setShowTimes(List<ShowTime> showTimes) { this.showTimes = showTimes; }
}
