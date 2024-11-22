import React from 'react';
import api from './api';

const Logout = ({ setIsLoggedIn }) => {
    const handleLogout = async () => {
        try {
            await api.post('/customers/logout');
            sessionStorage.clear(); // Clear session data
            setIsLoggedIn(false); // Update state
            alert('Logged out successfully!');
        } catch (error) {
            console.error('Logout error:', error.response?.data || error.message);
            alert('Logout failed: ' + (error.response?.data || error.message));
        }
    };

    return <button onClick={handleLogout}>Logout</button>;
};

export default Logout;
