import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './ConfirmationPage.css';

const ConfirmationPage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const bookingDetails = location.state?.bookingDetails;

    if (!bookingDetails) {
        return (
            <div className="no-booking-details">
                <h1>No Booking Details Found</h1>
                <button
                    onClick={() => navigate('/')}
                    className="back-button"
                >
                    Back to Home
                </button>
            </div>
        );
    }

    const { email, receiptId, receiptDate, totalAmount, tickets } = bookingDetails;

    return (
        <div className="confirmation-page">
            <h1>Booking Confirmation</h1>
            <p>Thank you for your booking!</p>
            <p><strong>Sent to :</strong> {bookingDetails.email}</p>
            <div className="receipt-details">
                <h2>Receipt Details</h2>
                <p><strong>Receipt ID:</strong> {receiptId}</p>
                <p><strong>Receipt Date:</strong> {receiptDate}</p>
                <p><strong>Total Amount:</strong> ${totalAmount.toFixed(2)}</p>

                <h2>Tickets</h2>
                <ul className="tickets-list">
                    {tickets.map((ticket, index) => (
                        <li key={index}>
                            <strong>Ticket ID: </strong>{ticket.id},<br />
                            <strong>Row:</strong> {ticket.row}, <strong>Seat:</strong> {ticket.seat} <br />
                            <strong>Show Date:</strong> {ticket.showDate}, <strong>Show Time:</strong> {ticket.showTime}
                        </li>
                    ))}
                </ul>
            </div>

            <button
                onClick={() => navigate('/')}
                className="back-button"
            >
                Back to Home
            </button>
        </div>
    );
};

export default ConfirmationPage;
