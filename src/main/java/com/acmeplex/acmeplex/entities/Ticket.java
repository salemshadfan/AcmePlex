package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private ShowTime showTime;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setSeat(Seat seat){
        this.seat=seat;
    }

    public void setShowTime(ShowTime showTime2) {
       this.showTime = showTime2;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
     public Customer getCustomer() {
        return this.customer;
     }
     


    public Seat getSeat() {
        return this.seat;
    }

    public ShowTime getShowtime() {
        return this.showTime;
    }
}

