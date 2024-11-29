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
    const [errors, setErrors] = useState({}); // To track field errors

    const validateFields = () => {
        const newErrors = {};

        if (!firstName.trim()) newErrors.firstName = 'First name is required';
        if (!lastName.trim()) newErrors.lastName = 'Last name is required';
        if (!email.trim()) newErrors.email = 'Email is required';
        if (!password.trim()) newErrors.password = 'Password is required';
        if (password !== confirmPassword) newErrors.confirmPassword = 'Passwords do not match';
        if (!cardNumber.trim()) newErrors.cardNumber = 'Card number is required';
        if (!expiryDate.trim()) newErrors.expiryDate = 'Expiry date is required';
        if (!cvv.trim()) newErrors.cvv = 'CVV is required';

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSignUp = async (e) => {
        e.preventDefault();

        if (!validateFields()) {
            return;
        }

        try {
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
        <div style={{ display: 'flex', height: '100vh', width: '100vw', backgroundColor: '#eef2f7' }}>
            <div
                style={{
                    flex: 1,
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                }}
            >
                <form
                    onSubmit={handleSignUp}
                    style={{
                        display: 'flex',
                        flexDirection: 'column',
                        width: '400px',
                        backgroundColor: 'white',
                        padding: '20px',
                        borderRadius: '10px',
                        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                    }}
                >
                    <h2 style={{ textAlign: 'center', color: '#333', marginBottom: '20px' }}>Sign Up</h2>
                    <input
                        type="text"
                        placeholder="First Name"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.firstName ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <input
                        type="text"
                        placeholder="Last Name"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.lastName ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.email ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.password ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <input
                        type="password"
                        placeholder="Confirm Password"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.confirmPassword ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <input
                        type="text"
                        placeholder="Card Number"
                        value={cardNumber}
                        onChange={(e) => setCardNumber(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.cardNumber ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <input
                        type="text"
                        placeholder="Expiry Date (MM/YY)"
                        value={expiryDate}
                        onChange={(e) => setExpiryDate(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.expiryDate ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <input
                        type="text"
                        placeholder="CVV"
                        value={cvv}
                        onChange={(e) => setCvv(e.target.value)}
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: errors.cvv ? '1px solid red' : '1px solid #ccc',
                        }}
                    />
                    <label style={{ marginBottom: '10px', fontSize: '14px', color: '#555' }}>
                        <input
                            type="checkbox"
                            checked={isAgreementChecked}
                            onChange={(e) => setIsAgreementChecked(e.target.checked)}
                            required
                            style={{ marginRight: '10px' }}
                        />
                        I agree to the annual payment of $20.
                    </label>
                    <button
                        type="submit"
                        disabled={!isAgreementChecked}
                        style={{
                            padding: '10px',
                            backgroundColor: '#4CAF50',
                            color: 'white',
                            border: 'none',
                            borderRadius: '4px',
                            cursor: 'pointer',
                            fontWeight: 'bold',
                        }}
                    >
                        Sign Up
                    </button>
                </form>
            </div>
        </div>
    );
};

export default SignUp;
