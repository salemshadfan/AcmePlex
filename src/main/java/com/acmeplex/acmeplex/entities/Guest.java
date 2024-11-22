package com.acmeplex.acmeplex.entities;

import jakarta.persistence.Entity;

@Entity
public class Guest extends Customer {
    // Additional fields or methods for Ordinary Users can be added here

    public double calculateRefund(double ticketPrice) {
        // Apply a 15% admin fee for refunds
        return ticketPrice * 0.85;
    }
}
