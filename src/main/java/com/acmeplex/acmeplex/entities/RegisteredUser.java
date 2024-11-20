package com.acmeplex.acmeplex.entities;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class RegisteredUser extends User {
    private double annualFee = 20.0; // Fixed annual fee
    private LocalDate registrationDate;

    public double calculateRefund(double ticketPrice) {
        // No admin fee for Registered Users
        return ticketPrice;
    }

    // Getters and Setters
    public double getAnnualFee() {
        return annualFee;
    }
    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
