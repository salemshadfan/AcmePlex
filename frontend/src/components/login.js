import React, { useState } from 'react';
import api from './api';

const Login = ({ setIsLoggedIn }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await api.post('/customers/login',null,{params: { email, password }});
            sessionStorage.setItem('isLoggedIn', 'true'); // Track login status
            setIsLoggedIn(true); // Update state
            alert('Login successful!');
        } catch (error) {
            console.error('Login error:', error.response?.data || error.message);
            alert('Login failed: ' + (error.response?.data || error.message));
        }
    };


    return (
        <form onSubmit={handleLogin}>
            <h2>Login</h2>
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
            <button onClick={handleLogin}>Login</button>
        </form>
    );
};

export default Login;
