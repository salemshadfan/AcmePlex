import React, { useState } from 'react';
import api from './api';

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
    const [errors, setErrors] = useState({ cardNumber: '', cvv: '' });

    const handleCardNumberChange = (e) => {
        const value = e.target.value.replace(/\D/g, ''); // Remove non-digits
        if (value.length <= 16) {
            setCardNumber(value);
            setErrors((prevErrors) => ({ ...prevErrors, cardNumber: value.length === 16 ? '' : 'Card number must be 16 digits.' }));
        }
    };

    const handleExpiryDateChange = (e) => {
        let value = e.target.value.replace(/\D/g, ''); // Remove non-digits
        if (value.length > 4) return;
        if (value.length > 2) {
            value = value.slice(0, 2) + '/' + value.slice(2);
        }
        setExpiryDate(value);
    };

    const handleCvvChange = (e) => {
        const value = e.target.value.replace(/\D/g, ''); // Remove non-digits
        if (value.length <= 3) {
            setCvv(value);
            setErrors((prevErrors) => ({ ...prevErrors, cvv: value.length === 3 ? '' : 'CVV must be 3 digits.' }));
        }
    };

    const handleSignUp = async (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert('Passwords do not match. Please try again.');
            return;
        }

        if (!isAgreementChecked) {
            alert('You must agree to the annual payment to proceed.');
            return;
        }

        if (errors.cardNumber || errors.cvv) {
            alert('Please fix the errors in the form before submitting.');
            return;
        }

        try {
            await api.post('/customers/signup', null, {
                params: { firstName, lastName, email, password, cardNumber, expiryDate, cvv },
            });
            alert('Sign-up successful! You can now log in.');
        } catch (error) {
            console.error('Sign-up error:', error.response?.data || error.message);
            alert('Sign-up failed: ' + (error.response?.data || error.message));
        }
    };

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh',  backgroundColor: '#f8f9fa'}}>
            <form onSubmit={handleSignUp} style={{ display: 'flex', flexDirection: 'column', width: '300px' }}>
                <h2 style={{ textAlign: 'center' }}>Sign Up</h2>
                <input
                    type="text"
                    placeholder="First Name"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />
                <input
                    type="text"
                    placeholder="Last Name"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />
                <input
                    type="password"
                    placeholder="Confirm Password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />

                <h3 style={{ textAlign: 'center' }}>Payment Information</h3>
                <input
                    type="text"
                    placeholder="Card Number"
                    value={cardNumber}
                    onChange={handleCardNumberChange}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />
                {errors.cardNumber && <span style={{ color: 'red', marginBottom: '10px' }}>{errors.cardNumber}</span>}
                <input
                    type="text"
                    placeholder="Expiry Date (MM/YY)"
                    value={expiryDate}
                    onChange={handleExpiryDateChange}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />
                <input
                    type="text"
                    placeholder="CVV"
                    value={cvv}
                    onChange={handleCvvChange}
                    required
                    style={{ marginBottom: '10px', padding: '10px' }}
                />
                {errors.cvv && <span style={{ color: 'red', marginBottom: '10px' }}>{errors.cvv}</span>}
                <label style={{ marginBottom: '10px', fontSize: '14px' }}>
                    <input
                        type="checkbox"
                        checked={isAgreementChecked}
                        onChange={(e) => setIsAgreementChecked(e.target.checked)}
                        required
                    />{' '}
                    By clicking this, you agree to an annual payment of $20 for registered users.
                </label>
                <button
                    type="submit"
                    disabled={!isAgreementChecked}
                    style={{
                        padding: '10px',
                        backgroundColor: isAgreementChecked ? 'gold' : '#ccc', // Changed to gold
                        color: isAgreementChecked ? 'black' : 'white',        // Changed text color for better contrast
                        border: 'none',
                        cursor: isAgreementChecked ? 'pointer' : 'not-allowed',
                    }}
                >
                    Sign up
                </button>
            </form>
        </div>
    );
};

export default SignUp;
