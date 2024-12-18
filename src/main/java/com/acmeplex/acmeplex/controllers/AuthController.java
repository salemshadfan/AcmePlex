package com.acmeplex.acmeplex.controllers;

import com.acmeplex.acmeplex.entities.Payment;
import com.acmeplex.acmeplex.entities.RegisteredUser;
import com.acmeplex.acmeplex.repositories.CustomerRepository;
import com.acmeplex.acmeplex.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>>  login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {
        RegisteredUser user = customerRepository.findRegisteredUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!user.login(email, password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        session.setAttribute("userId", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("isRegistered", true);
        

        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getId());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
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

        // Create and save the Payment entity
        Payment payment = new Payment();
        payment.setCreditCardNumber(creditCardNumber);
        payment.setCcv(cvc);
        payment.setExpiryDate(expiryDate);
        payment.setCardType(cardType);
        paymentRepository.save(payment); // Save the payment entity to the database

        // Create the RegisteredUser entity
        RegisteredUser newUser = new RegisteredUser();
        newUser.setFirstName(FirstName);
        newUser.setLastName(LastName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPaymentmethod(payment); // Associate the saved Payment entity
        newUser.setRegistrationDate(LocalDate.now());
        newUser.chargePayment(payment, newUser.getAnnualFee());
        customerRepository.save(newUser); // Save the RegisteredUser entity


        return ResponseEntity.ok("Sign-up successful");
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return ResponseEntity.ok("Logged out successfully");
    }



}


