package com.acmeplex.acmeplex.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class RegisteredUser extends Customer {
    private double annualFee = 20.0;
    private LocalDate registrationDate;
    private String password;
    @ManyToOne
    @JoinColumn(name = "paymentmethod_id")
    private Payment paymentmethod;

    public Payment getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(Payment paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    // Constructor
    public RegisteredUser(String FirstName, String LastName, String email, String password, LocalDate registrationDate, String creditCardNumber, String cardType,
                          String ccv, String expiryDate ) {
        super(FirstName,LastName,email, true); // isRegistered always true
        this.password = password;
        this.registrationDate = registrationDate;
        this.paymentmethod= new Payment(creditCardNumber,cardType,ccv,expiryDate,annualFee);

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
