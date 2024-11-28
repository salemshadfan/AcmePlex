import React from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import api from './api'; // Your API utility

const Logout = ({ setIsLoggedIn }) => {
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            await api.post('/customers/logout'); // Call your logout API
            sessionStorage.clear(); // Clear session storage
            setIsLoggedIn(false); // Update the state
            toast.success('Logged out successfully!'); // Show success toast
            navigate('/login'); // Redirect to login page
        } catch (error) {
            toast.error('Logout failed: ' + (error.response?.data || error.message)); // Show error toast
        }
    };

    return <button onClick={handleLogout}>Logout</button>;
};

export default Logout;
