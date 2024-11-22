package com.acmeplex.acmeplex.entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private boolean isRegistered;

    public Customer(String firstName, String lastName,String email, boolean isRegistered) {
        this.email = email;
        this.isRegistered = isRegistered;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer() {

    }

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

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
