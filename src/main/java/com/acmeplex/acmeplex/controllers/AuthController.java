package com.acmeplex.acmeplex.controllers;

import com.acmeplex.acmeplex.entities.RegisteredUser;
import com.acmeplex.acmeplex.repositories.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class AuthController {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    /**
     * Sign up a new Registered User.
     * Requires email, password, and payment of the annual fee.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(
            @RequestBody RegisteredUser request,
            @RequestParam double payment
    ) {
        try {
            // Validate payment
            if (payment < 20.0) {
                return ResponseEntity.badRequest().body("Annual fee must be paid in full to register.");
            }

            // Check if email is already registered
            if (registeredUserRepository.findByEmail(request.getEmail()) != null) {
                return ResponseEntity.badRequest().body("Email is already registered.");
            }

            // Create a new Registered User
            RegisteredUser newUser = new RegisteredUser();
            newUser.setEmail(request.getEmail());
            newUser.setPassword(request.getPassword()); // Hash this in production
            newUser.setRegistrationDate(LocalDate.now());

            registeredUserRepository.save(newUser);
            return ResponseEntity.ok("Registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred during sign-up.");
        }
    }

    /**
     * Login an existing Registered User.
     * Requires email and password.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisteredUser request) {
        try {
            RegisteredUser user = registeredUserRepository.findByEmail(request.getEmail());

            if (user == null || !user.getPassword().equals(request.getPassword())) {
                return ResponseEntity.status(401).body("Invalid email or password.");
            }

            // In production, generate and return a JWT token here
            return ResponseEntity.ok("Login successful!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred during login.");
        }
    }

    /**
     * Guest checkout.
     * Simply uses the email for the current transaction without storing it.
     */
    @PostMapping("/guest/checkout")
    public ResponseEntity<?> guestCheckout(@RequestParam String email) {
        // Use the email for confirmation or other guest-specific operations
        return ResponseEntity.ok("Guest checkout successful. Confirmation sent to " + email);
    }
}
