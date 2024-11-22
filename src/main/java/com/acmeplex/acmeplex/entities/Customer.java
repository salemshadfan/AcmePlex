package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private boolean isRegistered;

    public Customer(String email, boolean isRegistered) {
        this.email = email;
        this.isRegistered = isRegistered;
    }

    public Customer() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    protected void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered; // Protected to control from subclasses
    }
}
