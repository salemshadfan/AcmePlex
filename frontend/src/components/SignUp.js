import React, { useState } from 'react';
import api from './api'; // Import the api instance

const SignUp = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [cardNumber, setCardNumber] = useState('');
    const [expiryDate, setExpiryDate] = useState('');
    const [cvv, setCvv] = useState('');
    const [isAgreementChecked, setIsAgreementChecked] = useState(false);

    const handleSignUp = async (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert('Passwords do not match.');
            return;
        }

        try {
            // Use the api instance to make the signup request
            await api.post('/customers/signup', null, {
                params: {
                    FirstName: firstName,
                    LastName: lastName,
                    creditCardNumber: cardNumber,
                    cvc: cvv,
                    expiryDate,
                    cardType: 'VISA', // Example hardcoded card type
                    email,
                    password,
                },
            });
            alert('Sign-up successful! You can now log in.');
        } catch (error) {
            console.error('Sign-up error:', error.response?.data || error.message);
            alert('Sign-up failed: ' + (error.response?.data || error.message));
        }
    };

    return (
        <div>
            <form onSubmit={handleSignUp}>
                <h2>Sign Up</h2>
                <input
                    type="text"
                    placeholder="First Name"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="Last Name"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    required
                />
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="Card Number"
                    value={cardNumber}
                    onChange={(e) => setCardNumber(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="Expiry Date (MM/YY)"
                    value={expiryDate}
                    onChange={(e) => setExpiryDate(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="CVV"
                    value={cvv}
                    onChange={(e) => setCvv(e.target.value)}
                    required
                />
                <label>
                    <input
                        type="checkbox"
                        checked={isAgreementChecked}
                        onChange={(e) => setIsAgreementChecked(e.target.checked)}
                        required
                    />
                    I agree to the annual payment of $20.
                </label>
                <button type="submit" disabled={!isAgreementChecked}>
                    Sign Up
                </button>
            </form>
        </div>
    );
};

export default SignUp;
