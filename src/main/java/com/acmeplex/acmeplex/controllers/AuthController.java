package com.acmeplex.acmeplex.controllers;

import com.acmeplex.acmeplex.entities.RegisteredUser;
import com.acmeplex.acmeplex.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/customers")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {
        RegisteredUser user = customerRepository.findRegisteredUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        // Store user ID and email in the session
        session.setAttribute("userId", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("isRegistered", true);

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @RequestParam String FirstName,
            @RequestParam String LastName,
            @RequestParam String creditCardNumber,
            @RequestParam String cvc,
            @RequestParam String expiryDate,
            @RequestParam String cardType,
            @RequestParam String email,
            @RequestParam String password) {
        if (customerRepository.findRegisteredUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        RegisteredUser newUser = new RegisteredUser(FirstName, LastName,email, password, java.time.LocalDate.now(), creditCardNumber, cardType,cvc,expiryDate);
        customerRepository.save(newUser);

        return ResponseEntity.ok("Sign-up successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return ResponseEntity.ok("Logged out successfully");
    }



}


