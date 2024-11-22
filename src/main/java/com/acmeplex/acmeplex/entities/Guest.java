package com.acmeplex.acmeplex.entities;

import jakarta.persistence.Entity;

@Entity
public class Guest extends Customer {

    // Constructor
    public Guest(String email) {
        super(email, false); // isRegistered always false
    }

    public Guest() {
        super(); // Default constructor for JPA
        setRegistered(false);
    }

    // Refund calculation
    public double calculateRefund(double ticketPrice) {
        return ticketPrice * 0.85; // Deduct 15% admin fee
    }
}
