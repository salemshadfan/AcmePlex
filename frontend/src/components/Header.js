import React from 'react';
import './Header.css'; // Ensure you create this file for custom styles

const Header = ({ onNavigate }) => {
    return (
        <header className="header">
            <div className="logo-container">
                <img src="logo.png" alt="AcmePlex Logo" className="logo" />
                <span className="cinema-name">AcmePlex</span>
            </div>
            <nav className="nav">
                <button onClick={() => onNavigate('showtimes')} className="nav-link" style={{ color: 'gold', fontSize: '18px', fontWeight: 'bold' }}>Showtimes</button>
                <button onClick={() => onNavigate('movies')} className="nav-link" style={{ color: 'gold', fontSize: '18px', fontWeight: 'bold' }}>Movies</button>
            </nav>
            <div className="auth-container">
                <button onClick={() => onNavigate('login')} className="auth-button" style={{ backgroundColor: 'gold', color: 'black', fontWeight: 'bold' }}>
                    <span className="auth-icon">👤</span> LOG IN/SIGN UP
                </button>
            </div>
        </header>
    );
};

export default Header;
