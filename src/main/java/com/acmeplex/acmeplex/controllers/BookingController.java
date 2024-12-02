package com.acmeplex.acmeplex.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acmeplex.acmeplex.entities.Customer;
import com.acmeplex.acmeplex.entities.Guest;
import com.acmeplex.acmeplex.entities.Payment;
import com.acmeplex.acmeplex.entities.Receipt;
import com.acmeplex.acmeplex.entities.RegisteredUser;
import com.acmeplex.acmeplex.entities.Seat;
import com.acmeplex.acmeplex.entities.Ticket;
import com.acmeplex.acmeplex.repositories.CustomerRepository;
import com.acmeplex.acmeplex.repositories.PaymentRepository;
import com.acmeplex.acmeplex.repositories.ReceiptRepository;
import com.acmeplex.acmeplex.repositories.SeatRepository;
import com.acmeplex.acmeplex.repositories.TicketRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @PostMapping("/book")
    public ResponseEntity<?> bookTickets(@RequestBody BookingRequest bookingRequest) {
        // Validate input
        System.err.println(bookingRequest.isGuest()); 
        if (bookingRequest.getSeatIds() == null || bookingRequest.getSeatIds().isEmpty()) {
            return ResponseEntity.badRequest().body("Seat selection is required.");
        }

        // Retrieve selected seats
        List<Seat> selectedSeats = seatRepository.findAllById(bookingRequest.getSeatIds());
        if (selectedSeats.isEmpty()) {
            return ResponseEntity.badRequest().body("No valid seats selected.");
        }

        // Ensure seats are available
        for (Seat seat : selectedSeats) {
            if (!seat.isAvailable()) {
                return ResponseEntity.badRequest().body("One or more selected seats are already booked.");
            }
        }
        Customer customer;

        if (bookingRequest.isGuest()) {
            // Validate guest details
            if (bookingRequest.getGuestFirstName() == null || bookingRequest.getGuestLastName() == null || bookingRequest.getGuestEmail() == null) {
                return ResponseEntity.badRequest().body("Guest details are required.");
            }
            Guest guest = new Guest();
            guest.setFirstName(bookingRequest.getGuestFirstName());
            guest.setLastName(bookingRequest.getGuestLastName());
            guest.setEmail(bookingRequest.getGuestEmail());
            customer = customerRepository.save(guest);
        } else {
            // Validate registered user
            customer = customerRepository.findById(bookingRequest.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID."));

            if (!(customer instanceof RegisteredUser)) {
                return ResponseEntity.badRequest().body("Customer must be a registered user.");
            }
        }

        // Create tickets and link to customer
        List<Ticket> tickets = new ArrayList<>();
        for (Seat seat : selectedSeats) {
            Ticket ticket = new Ticket();
            ticket.setSeat(seat);
            ticket.setPrice(bookingRequest.getPrice());
            ticket.setShowTime(seat.getShowTime());
            ticket.setCustomer(customer);
            tickets.add(ticket);

            // Mark seat as unavailable
            seat.setAvailable(false);
        }

        // Save tickets and update seats
        ticketRepository.saveAll(tickets);
        seatRepository.saveAll(selectedSeats);

        // Save payment
        Payment payment = new Payment();
        payment.setAmount(tickets.size() * bookingRequest.getPrice());
        if (bookingRequest.isGuest()) {
            payment.setCreditCardNumber(bookingRequest.getCreditCardNumber());
            payment.setCardType(bookingRequest.getCardType());
            payment.setCcv(bookingRequest.getCcv());
            payment.setExpiryDate(bookingRequest.getExpiryDate());
            customer.chargePayment(payment, bookingRequest.getPrice());

        } else {
            // Registered user payment
            assert customer instanceof RegisteredUser;
            RegisteredUser registeredUser = (RegisteredUser) customer;
            payment.setCreditCardNumber(registeredUser.getPaymentmethod().getCreditCardNumber());
            payment.setCardType(registeredUser.getPaymentmethod().getCardType());
            payment.setCcv(registeredUser.getPaymentmethod().getCcv());
            payment.setExpiryDate(registeredUser.getPaymentmethod().getExpiryDate());
            registeredUser.chargePayment(payment, bookingRequest.getPrice());
        }


        payment.setTicket(tickets.get(0));
        paymentRepository.save(payment);

        // Create and save receipt
        Receipt receipt = new Receipt();
        receipt.setReceiptDate(java.time.LocalDate.now().toString());
        receipt.setTotalAmount(payment.getAmount());
        receipt.setTicket(tickets.get(0)); // Associate the receipt with the first ticket
        receiptRepository.save(receipt);

        // Prepare receipt response
        Map<String, Object> receiptResponse = new HashMap<>();
        receiptResponse.put("email", receipt.getTicket().getCustomer().getEmail());
        receiptResponse.put("receiptId", receipt.getId());
        receiptResponse.put("receiptDate", receipt.getReceiptDate());
        receiptResponse.put("totalAmount", receipt.getTotalAmount());

        // Include ticket details in the response
        List<Map<String, Object>> ticketDetails = new ArrayList<>();
        for (Ticket ticket : tickets) {
            Map<String, Object> ticketDetail = new HashMap<>();
            ticketDetail.put("id", ticket.getId());
            ticketDetail.put("row", ticket.getSeat().getRowNumber());
            ticketDetail.put("seat", ticket.getSeat().getSeat_Number());
            ticketDetail.put("showDate", ticket.getShowtime().getDate());
            ticketDetail.put("showTime", ticket.getShowtime().getStartTime());
            ticketDetails.add(ticketDetail);
        }
        receiptResponse.put("tickets", ticketDetails);

        return ResponseEntity.ok(receiptResponse);
    }



public static class BookingRequest {
    @JsonProperty("isGuest")
    private boolean isGuest; // Indicates if the booking is for a guest
    private Long customerId; // Only relevant for registered users
    private List<Long> seatIds; // List of seat IDs to book
    private double price; // Total price of the booking

    // Guest details
    private String guestFirstName;
    private String guestLastName;
    private String guestEmail;

    // Payment information (only for guests)
    private String creditCardNumber;
    private String cardType;
    private String ccv;
    private String expiryDate;

    // Getters and Setters
    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGuestFirstName() {
        return guestFirstName;
    }

    public void setGuestFirstName(String guestFirstName) {
        this.guestFirstName = guestFirstName;
    }

    public String getGuestLastName() {
        return guestLastName;
    }

    public void setGuestLastName(String guestLastName) {
        this.guestLastName = guestLastName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}


    
}
