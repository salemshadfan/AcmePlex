package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class RegisteredUser extends Customer {
    private double annualFee = 20.0;
    private LocalDate registrationDate;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
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




    public boolean login(String inputEmail, String inputPassword) {
        return this.getEmail().equals(inputEmail) && this.password.equals(inputPassword);
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

    @Override
    public void chargePayment(Payment payment, double amount) {
        System.out.println("payment processed");
    }
}
