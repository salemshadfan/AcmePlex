import React, { useState } from 'react';
import api from './api';
import { useNavigate } from 'react-router-dom';

const Login = ({ setIsLoggedIn }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            await api.post('/customers/login', null, { params: { email, password } });
            sessionStorage.setItem('isLoggedIn', 'true'); // Track login status
            setIsLoggedIn(true); // Update state
            alert('Login successful!');
            navigate('/');
        } catch (error) {
            console.error('Login error:', error.response?.data || error.message);
            alert('Login failed: ' + (error.response?.data || error.message));
        }
    };

    return (
        <div style={{ display: 'flex', height: '100vh', width: '100vw' }}>

            <div
                style={{
                    flex: 1,
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    backgroundColor: '#f8f9fa', // Light background for the form
                }}
            >
                <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', width: '300px' }}>
                    <h2 style={{ textAlign: 'center' }}>Login</h2>
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={{ marginBottom: '10px', padding: '10px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{ marginBottom: '10px', padding: '10px', borderRadius: '4px', border: '1px solid #ccc' }}
                    />
                    <button
                        type="submit"
                        style={{
                            padding: '10px',
                            backgroundColor: 'gold',
                            color: 'black',
                            border: 'none',
                            borderRadius: '4px',
                            cursor: 'pointer',
                        }}
                    >
                        Login
                    </button>
                </form>
            </div>
        </div>
    );

};

export default Login;
