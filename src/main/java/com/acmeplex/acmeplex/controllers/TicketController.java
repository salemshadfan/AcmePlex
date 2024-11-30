package com.acmeplex.acmeplex.controllers;

import com.acmeplex.acmeplex.entities.Ticket;
import com.acmeplex.acmeplex.repositories.PaymentRepository;
import com.acmeplex.acmeplex.repositories.ReceiptRepository;
import com.acmeplex.acmeplex.repositories.TicketRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    // Endpoint to get tickets for the logged-in user
    @GetMapping
    public ResponseEntity<?> getUserTickets(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please log in to view your tickets.");
        }

        // Find tickets associated with the logged-in user
        List<Ticket> tickets = ticketRepository.findByCustomerId(userId);
        return ResponseEntity.ok(tickets);
    }

    // Endpoint to cancel a ticket
    @PostMapping("/cancel")
    public ResponseEntity<?> cancelTicket(@RequestBody CancelRequest cancelRequest, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        // Find the ticket by ID
        Optional<Ticket> optionalTicket = ticketRepository.findById(cancelRequest.getTicketId());
        if (optionalTicket.isEmpty()) {
            return ResponseEntity.badRequest().body("Ticket not found.");
        }

        Ticket ticket = optionalTicket.get();

        // Check if the ticket belongs to the logged-in user or if it's for a guest
        if (ticket.getCustomer().isRegistered()) {
            // Registered user cancelation
            
            if (!ticket.getCustomer().getId().equals(userId)) {
                return ResponseEntity.badRequest().body("Unauthorized: You cannot cancel this ticket.");
            }
        } else {
            // Guest cancelation
            if (!cancelRequest.getEmail().equals(ticket.getCustomer().getEmail())) {
                return ResponseEntity.badRequest().body("Invalid email for the ticket.");
            }
        }

        // Check if the cancelation is allowed (72 hours before showtime)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime showTime = LocalDateTime.of(ticket.getShowtime().getDate(), ticket.getShowtime().getStartTime());
        System.err.println(now);
            System.err.println(showTime);
        long hoursUntilShow = now.until(showTime, ChronoUnit.HOURS);
        if (hoursUntilShow < 72) {
            return ResponseEntity.badRequest().body("Tickets cannot be canceled less than 72 hours before the showtime.");
        }

        // Calculate refund amount
        double refundAmount = ticket.getCustomer().isRegistered()
                ? ticket.getPrice() // Full refund for registered users
                : ticket.getPrice() * 0.85; // 15% fee for guests

        // Mark the ticket as canceled
        ticket.getSeat().setAvailable(true);
        paymentRepository.deleteByTicketId(ticket.getId());
        receiptRepository.deleteByTicketId(ticket.getId());
        ticketRepository.delete(ticket); // Remove the ticket or mark it as canceled

        return ResponseEntity.ok(new CancelResponse(refundAmount));
    }

    // Nested class for cancel request
    public static class CancelRequest {
        private Long ticketId;
        private String email; // Optional for guests

        // Getters and setters
        public Long getTicketId() {
            return ticketId;
        }

        public void setTicketId(Long ticketId) {
            this.ticketId = ticketId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    // Nested class for cancel response
    public static class CancelResponse {
        private double refundAmount;

        public CancelResponse(double refundAmount) {
            this.refundAmount = refundAmount;
        }

        public double getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(double refundAmount) {
            this.refundAmount = refundAmount;
        }
    }
}
