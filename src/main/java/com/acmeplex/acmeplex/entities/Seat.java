package com.acmeplex.acmeplex.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`row_Number`")
    private int row_Number;
    private int seat_Number;
    private boolean isAvailable;


    @ManyToOne
    @JoinColumn(name = "showtime_id")
    @JsonBackReference
    private ShowTime showTime;


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getRowNumber() { return row_Number; }
    public void setRowNumber(int rowNumber) { this.row_Number = rowNumber; }
    public int getSeat_Number() { return seat_Number; }
    public void setSeat_Number(int seat_Number) { this.seat_Number = seat_Number; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public ShowTime getShowTime() { return showTime; }
    public void setShowTime(ShowTime showTime) { this.showTime = showTime; }
}
