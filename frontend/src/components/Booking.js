import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './Booking.css';

const Booking = () => {
    const { id } = useParams(); // Showtime ID from the URL
    const navigate = useNavigate();
    const [seats, setSeats] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [isGuest, setIsGuest] = useState(!sessionStorage.getItem('isLoggedIn')); // Check if user is logged in
    const [guestDetails, setGuestDetails] = useState({
        firstName: '',
        lastName: '',
        email: '',
        paymentInfo: {
            creditCardNumber: '',
            cardType: '',
            ccv: '',
            expiryDate: '',
        },
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchSeats = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/showtimes/${id}/seats`);
                if (!response.ok) {
                    throw new Error('Failed to fetch seats');
                }
                const data = await response.json();
                setSeats(data);
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        fetchSeats();
    }, [id]);

    const toggleSeatSelection = (seatId) => {
        if (selectedSeats.includes(seatId)) {
            setSelectedSeats(selectedSeats.filter((id) => id !== seatId));
        } else {
            setSelectedSeats([...selectedSeats, seatId]);
        }
    };

    const handleGuestDetailsChange = (e) => {
        const { name, value } = e.target;
        if (name in guestDetails.paymentInfo) {
            setGuestDetails({
                ...guestDetails,
                paymentInfo: {
                    ...guestDetails.paymentInfo,
                    [name]: value,
                },
            });
        } else {
            setGuestDetails({
                ...guestDetails,
                [name]: value,
            });
        }
    };

    const handleConfirmBooking = async () => {
        const bookingRequest = {
            isGuest,
            customerId: sessionStorage.getItem('userId'), // For logged-in users
            guestFirstName: guestDetails.firstName,
            guestLastName: guestDetails.lastName,
            guestEmail: guestDetails.email,
            creditCardNumber: guestDetails.paymentInfo?.creditCardNumber,
            cardType: guestDetails.paymentInfo?.cardType,
            ccv: guestDetails.paymentInfo?.ccv,
            expiryDate: guestDetails.paymentInfo?.expiryDate,
            seatIds: selectedSeats,
            price: 10.0, // Replace with the price of a ticket if dynamic pricing is required
        };

        try {
            const response = await fetch('http://localhost:8080/api/bookings/book', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(bookingRequest),
            });

            if (!response.ok) {
                throw new Error('Booking failed');
            }

            const receipt = await response.json();

            // Navigate to confirmation page
            navigate('/confirmation', { state: { bookingDetails: receipt } });
        } catch (error) {
            console.error('Booking Error:', error);
            alert('Booking failed. Please try again.');
        }
    };

    if (loading) {
        return <div>Loading seats...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    // Group seats by row for better layout and add row labels
    const groupedSeats = seats.reduce((rows, seat) => {
        if (!rows[seat.rowNumber]) rows[seat.rowNumber] = [];
        rows[seat.rowNumber].push(seat);
        return rows;
    }, {});

    return (
        <div className="booking-page">
            <h1>Seat Selection</h1>
            <div className="screen">SCREEN</div>
            <div className="seat-map">
                {seats.reduce((rows, seat) => {
                    const rowIndex = seat.rowNumber - 1;
                    if (!rows[rowIndex]) rows[rowIndex] = [];
                    rows[rowIndex].push(seat);
                    return rows;
                }, []).map((row, rowIndex) => (
                    <div className="seat-row" key={`row-${rowIndex}`}>
                        <span className="row-label">Row {rowIndex + 1}</span>
                        {row.map((seat) => (
                            <button
                                key={seat.id}
                                className={`seat ${seat.available ? '' : 'unavailable'} ${
                                    selectedSeats.includes(seat.id) ? 'selected' : ''
                                }`}
                                disabled={!seat.available}
                                onClick={() => toggleSeatSelection(seat.id)}
                            >
                                {seat.seat_Number}
                            </button>
                        ))}
                    </div>
                ))}
</div>

            {isGuest && (
                <div className="guest-details">
                    <h2>Guest Details</h2>
                    <input
                        type="text"
                        name="firstName"
                        placeholder="First Name"
                        value={guestDetails.firstName}
                        onChange={handleGuestDetailsChange}
                    />
                    <input
                        type="text"
                        name="lastName"
                        placeholder="Last Name"
                        value={guestDetails.lastName}
                        onChange={handleGuestDetailsChange}
                    />
                    <input
                        type="email"
                        name="email"
                        placeholder="Email"
                        value={guestDetails.email}
                        onChange={handleGuestDetailsChange}
                    />

                    <h3>Payment Information</h3>
                    <input
                        type="text"
                        name="creditCardNumber"
                        placeholder="Credit Card Number"
                        value={guestDetails.paymentInfo.creditCardNumber}
                        onChange={handleGuestDetailsChange}
                    />
                    <input
                        type="text"
                        name="cardType"
                        placeholder="Card Type"
                        value={guestDetails.paymentInfo.cardType}
                        onChange={handleGuestDetailsChange}
                    />
                    <input
                        type="text"
                        name="ccv"
                        placeholder="CCV"
                        value={guestDetails.paymentInfo.ccv}
                        onChange={handleGuestDetailsChange}
                    />
                    <input
                        type="text"
                        name="expiryDate"
                        placeholder="Expiry Date (MM/YY)"
                        value={guestDetails.paymentInfo.expiryDate}
                        onChange={handleGuestDetailsChange}
                    />
                </div>
            )}

            <button
                className="confirm-booking-button"
                disabled={selectedSeats.length === 0 || (isGuest && !guestDetails.email)}
                onClick={handleConfirmBooking}
            >
                Confirm Booking
            </button>
        </div>
    );
};

export default Booking;