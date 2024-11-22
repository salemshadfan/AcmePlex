package com.acmeplex.acmeplex.entities;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class RegisteredUser extends Customer {
    private double annualFee = 20.0;
    private LocalDate registrationDate;
    private String password;

    // Constructor
    public RegisteredUser(String email, String password, LocalDate registrationDate) {
        super(email, true); // isRegistered always true
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public RegisteredUser() {
        super(); // Default constructor for JPA
        setRegistered(true);
    }

    // Refund calculation
    public double calculateRefund(double ticketPrice, boolean within72Hours) {
        if (within72Hours) {
            return ticketPrice;
        }
        throw new IllegalStateException("Cancellation is allowed only up to 72 hours prior to the show.");
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
