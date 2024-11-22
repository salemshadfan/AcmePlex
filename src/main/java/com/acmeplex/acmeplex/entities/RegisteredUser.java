package com.acmeplex.acmeplex.entities;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class RegisteredUser extends Guest {
    private double annualFee = 20.0; // Fixed annual fee
    private LocalDate registrationDate;
    private String password;

    public double calculateRefund(double ticketPrice) {
        // No admin fee for Registered Users
        return ticketPrice;
    }

    // Getters and Setters
    public String getPassword() {return this.password;}
    public void setPassword(String password) {this.password = password;}
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
