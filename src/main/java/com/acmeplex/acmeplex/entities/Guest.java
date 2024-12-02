package com.acmeplex.acmeplex.entities;

import jakarta.persistence.Entity;

@Entity
public class Guest extends Customer {

    // Constructor
    public Guest(String FirstName, String LastName,String email) {
        super(FirstName,LastName,email, false); // isRegistered always false
    }

    public Guest() {
        super(); // Default constructor for JPA
        setRegistered(false);
    }

   
}
