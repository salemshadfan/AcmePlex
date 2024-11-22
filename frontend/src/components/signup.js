import React, { useState } from 'react';
import api from './api';

const SignUp = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignUp = async (e) => {
        e.preventDefault();
        try {
            await api.post('/customers/signup', null,{params: { email, password }});
            alert('Sign-up successful! You can now log in.');
        } catch (error) {
            console.error('Sign-up error:', error.response?.data || error.message);
            alert('Sign-up failed: ' + (error.response?.data || error.message));
        }
    };

    return (
        <form onSubmit={handleSignUp}>
            <h2>Sign Up</h2>
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
            <button onClick={handleSignUp}>Sign up</button>
        </form>
    );
};

export default SignUp;
