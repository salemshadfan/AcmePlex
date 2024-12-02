import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'; // Import Link and useNavigate
import './Header.css'; // Ensure this file contains your custom styles
import logo from '../assets/logo.png';

const Header = ({ isLoggedIn, setIsLoggedIn }) => {
    const [searchQuery, setSearchQuery] = useState(''); // State for the search input
    const navigate = useNavigate(); // Hook for programmatic navigation

    const handleLogout = () => {
        sessionStorage.clear(); // Clear session data
        setIsLoggedIn(false); // Update logged-in state
        navigate('/'); // Redirect to the home page
    };

    const handleSearch = () => {
        if (searchQuery.trim()) {
            navigate(`/search?query=${encodeURIComponent(searchQuery)}`); // Navigate to search results page
        }
    };

    return (
        <header className="header">
            <div className="logo-container">
                <img src={logo} alt="AcmePlex Logo" className="logo" />
                <Link to='/' className="cinema-name">AcmePlex</Link>
            </div>
            <nav className="nav">
                <Link to="/" className="nav-link" style={{ color: 'gold', fontSize: '18px', fontWeight: 'bold' }}>
                    MOVIES
                </Link>
                <Link to="/refund" className="nav-link" style={{ color: 'gold', fontSize: '18px', fontWeight: 'bold' }}>
                    CANCEL BOOKING
                </Link>
            </nav>
            <div className="search-container">
                <input
                    type="text"
                    placeholder="Search for a movie..."
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    className="search-input"
                />
                <button
                    onClick={handleSearch}
                    className="search-button"
                    style={{ backgroundColor: 'gold', color: 'black', fontWeight: 'bold' }}
                >
                    Search
                </button>
            </div>
            <div className="auth-container">
                {isLoggedIn ? (
                    <button
                        onClick={handleLogout}
                        className="auth-button"
                        style={{ backgroundColor: 'gold', color: 'black', fontWeight: 'bold' }}
                    >
                        <span className="auth-icon">👤</span> LOG OUT
                    </button>
                ) : (
                    <button
                        onClick={() => navigate('/login')}
                        className="auth-button"
                        style={{ backgroundColor: 'gold', color: 'black', fontWeight: 'bold' }}
                    >
                        <span className="auth-icon">👤</span> LOG IN/SIGN UP
                    </button>
                )}
            </div>
        </header>
    );
};

export default Header;