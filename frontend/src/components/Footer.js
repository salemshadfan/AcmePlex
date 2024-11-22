import React from 'react';
import './Footer.css';

const Footer = ({ setCurrentPage }) => {
    return (
        <footer className="footer">
            <div className="footer-links">
                <button onClick={() => setCurrentPage('about')} className="footer-link">About Us</button>
                <button onClick={() => setCurrentPage('contact')} className="footer-link">Contact Us</button>
                <button onClick={() => setCurrentPage('registered-users')} className="footer-link">Registered Users</button>
            </div>
        </footer>
    );
};

export default Footer;
