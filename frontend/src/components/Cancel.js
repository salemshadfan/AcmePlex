import React, { useEffect, useState } from 'react';
import './CancelPage.css';

const CancelPage = ({ isLoggedIn }) => {
    const [tickets, setTickets] = useState([]); // Tickets for logged-in users
    const [selectedTicket, setSelectedTicket] = useState(null); // Ticket selected for cancelation
    const [guestTicketId, setGuestTicketId] = useState(''); // Ticket ID for guest cancelation
    const [guestEmail, setGuestEmail] = useState(''); // Email for guest cancelation
    const [refundMessage, setRefundMessage] = useState(''); // Refund confirmation message
    const [loading, setLoading] = useState(false); // Loading state
    const [error, setError] = useState(null); // Error message

    // Debug isLoggedIn value
    useEffect(() => {
        
    }, [isLoggedIn]);

    // Fetch tickets for logged-in users
    useEffect(() => {
        if (isLoggedIn) {
            setLoading(true); // Start loading
            const fetchTickets = async () => {
                try {
                    const response = await fetch('http://localhost:8080/api/tickets', {
                        credentials: 'include', // Ensure cookies/session are sent with the request
                    });

                    if (!response.ok) {
                        throw new Error('Failed to fetch tickets');
                    }

                    const data = await response.json();
                    setTickets(data); // Store tickets
                } catch (err) {
                    setError(err.message);
                } finally {
                    setLoading(false); // Stop loading
                }
            };

            fetchTickets();
        }
    }, [isLoggedIn]);

    const handleCancelTicket = async () => {
        try {
            const payload = isLoggedIn
                ? { ticketId: selectedTicket?.id }
                : { ticketId: guestTicketId, email: guestEmail };
    
            const response = await fetch('http://localhost:8080/api/tickets/cancel', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                credentials: 'include', // Send session cookies if logged in
                body: JSON.stringify(payload),
            });
    
            let responseMessage = '';
    
            // Check the response content type
            const contentType = response.headers.get('Content-Type');
            if (contentType && contentType.includes('application/json')) {
                // Parse as JSON if content type is JSON
                const responseData = await response.json();
                responseMessage = responseData?.refundAmount
            } else {
                // Parse as plain text for non-JSON responses
                responseMessage = await response.text();
            }
    
            if (!response.ok) {
                setRefundMessage(`Error: ${responseMessage}`);
                return;
            }
            console.log(responseMessage)
            // Success: Display refund message
            setRefundMessage(`Ticket canceled successfully. Refund Amount: $${responseMessage}`);
            if (isLoggedIn) {
                setTickets((prevTickets) =>
                    prevTickets.filter((ticket) => ticket.id !== selectedTicket?.id)
                );
            }
            setSelectedTicket(null);
            setGuestTicketId('');
            setGuestEmail('');
        } catch (err) {
            // Handle unexpected errors
            setRefundMessage('Error: Unable to process your request. Please try again later.');
        }
    };
    

    // Conditional rendering for logged-in users
    if (loading) {
        return <p>Loading your tickets...</p>;
    }

    if (error) {
        return <p className="error-message">{error}</p>;
    }

    if (isLoggedIn) {
        return (
            <div className="cancel-page">
                <h1>Cancel Your Ticket</h1>
                {tickets.length > 0 ? (
                    <div className="ticket-list">
                        <h2>Your Tickets</h2>
                        <ul>
                            {tickets.map((ticket) => (
                                <li key={ticket.id}>
                                    <div>
                                        <strong>Ticket ID:</strong> {ticket.id}
                                    </div>
                                    <div>
                                        <strong>Showtime:</strong> {new Date(ticket.showtime.date + 'T' + ticket.showtime.startTime).toLocaleString()}
                                    </div>
                                    <div>
                                        <strong>Seat:</strong> {ticket.seat.seatNumber}
                                    </div>
                                    <div>
                                        <strong>Price:</strong> ${ticket.price}
                                    </div>
                                    <button
                                        onClick={() => setSelectedTicket(ticket)}
                                        className="select-ticket-button"
                                    >
                                        Select for Cancelation
                                    </button>
                                </li>
                            ))}
                        </ul>
                    </div>
                ) : (
                    <p>No tickets available for cancelation.</p>
                )}

                {selectedTicket && (
                    <div className="selected-ticket-section">
                        <h2>Selected Ticket</h2>
                        <p>
                            <strong>Ticket ID:</strong> {selectedTicket.id}
                        </p>
                        <p>
                            <strong>Showtime:</strong> {new Date(selectedTicket.showtime.date + 'T' + selectedTicket.showtime.startTime).toLocaleString()}
                        </p>
                        <p>
                            <strong>Seat:</strong> {selectedTicket.seat.seatNumber}
                        </p>
                        <p>
                            <strong>Price:</strong> ${selectedTicket.price}
                        </p>
                        <button onClick={handleCancelTicket} className="confirm-cancel-button">
                            Confirm Cancelation
                        </button>
                    </div>
                )}

                {refundMessage && (
                    <div className="refund-message">
                        <p>{refundMessage}</p>
                    </div>
                )}
            </div>
        );
    }

    // Guest user flow
    return (
        <div className="cancel-page">
            <h1>Cancel Your Ticket</h1>
            <div className="guest-cancel-section">
                <h2>Guest Cancelation</h2>
                <form
                    onSubmit={(e) => {
                        e.preventDefault();
                        handleCancelTicket();
                    }}
                >
                    <div>
                        <label htmlFor="ticketId">Ticket ID:</label>
                        <input
                            type="text"
                            id="ticketId"
                            value={guestTicketId}
                            onChange={(e) => setGuestTicketId(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label htmlFor="email">Email:</label>
                        <input
                            type="email"
                            id="email"
                            value={guestEmail}
                            onChange={(e) => setGuestEmail(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="cancel-button">
                        Cancel Ticket
                    </button>
                </form>
            </div>

            {refundMessage && (
                <div className="refund-message">
                    <p>{refundMessage}</p>
                </div>
            )}
        </div>
    );
};

export default CancelPage;
