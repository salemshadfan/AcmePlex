package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creditCardNumber;
    private String cardType;
    private String ccv;
    private String expiryDate;
    private double amount;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Payment(String creditCardNumber, String cardType, String ccv, String expiryDate, double amount) {
        this.creditCardNumber = creditCardNumber;
        this.cardType = cardType;
        this.ccv = ccv;
        this.expiryDate = expiryDate;
        this.amount = amount;

    }

    public Payment() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCreditCardNumber() {return this.creditCardNumber;}
    public void setCreditCardNumber(String creditCardNumber) {this.creditCardNumber = creditCardNumber;}
    public String getCardType() {return this.cardType;}
    public void setCardType(String cardType) {this.cardType = cardType;}
    public String getCcv() {return this.ccv;}
    public void setCcv(String ccv) {this.ccv = ccv;}
    public String getExpiryDate() {return this.expiryDate;}
    public void setExpiryDate(String expiryDate) {this.expiryDate = expiryDate;}
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
