package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rowNumber;
    private int seatNumber;
    private boolean isAvailable;


    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private ShowTime showTime;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getRowNumber() { return rowNumber; }
    public void setRowNumber(int rowNumber) { this.rowNumber = rowNumber; }
    public int getSeatNumber() { return seatNumber; }
    public void setSeatNumber(int seatNumber) { this.seatNumber = seatNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public ShowTime getShowTime() { return showTime; }
    public void setShowTime(ShowTime showTime) { this.showTime = showTime; }
}
