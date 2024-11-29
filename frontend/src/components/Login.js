import React, { useState } from 'react';
import api from './api';
import { useNavigate, Link } from 'react-router-dom';

const Login = ({ setIsLoggedIn }) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await api.post('/customers/login', null, { params: { email, password } });
            const { userId } = response.data;
            sessionStorage.setItem('isLoggedIn', 'true');
            sessionStorage.setItem('userId', userId);
            setIsLoggedIn(true);
            alert('Login successful!');
            navigate('/');
        } catch (error) {
            console.error('Login error:', error.response?.data || error.message);
            alert('Login failed: ' + (error.response?.data || error.message));
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
                    flexDirection: 'column',
                }}
            >
                <form
                    onSubmit={handleLogin}
                    style={{
                        display: 'flex',
                        flexDirection: 'column',
                        width: '300px',
                        backgroundColor: 'white',
                        padding: '20px',
                        borderRadius: '10px',
                        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
                    }}
                >
                    <h2 style={{ textAlign: 'center', color: '#333', marginBottom: '20px' }}>Login</h2>
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: '1px solid #ccc',
                            fontSize: '14px',
                        }}
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{
                            marginBottom: '10px',
                            padding: '10px',
                            borderRadius: '4px',
                            border: '1px solid #ccc',
                            fontSize: '14px',
                        }}
                    />
                    <button
                        type="submit"
                        style={{
                            padding: '10px',
                            backgroundColor: '#4CAF50',
                            color: 'white',
                            border: 'none',
                            borderRadius: '4px',
                            cursor: 'pointer',
                            fontWeight: 'bold',
                            fontSize: '14px',
                        }}
                    >
                        Login
                    </button>
                </form>
                <div style={{ marginTop: '20px', textAlign: 'center' }}>
                    <p style={{ color: '#555', fontSize: '14px' }}>
                        Don't have an account?{' '}
                        <Link
                            to="/signup"
                            style={{
                                color: '#3498db',
                                textDecoration: 'none',
                                fontWeight: 'bold',
                            }}
                        >
                            Sign up
                        </Link>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default Login;
