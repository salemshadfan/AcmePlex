import React from 'react';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-links">
                <Link to="/about" className="footer-link">About Us</Link>
                <Link to="/contact" className="footer-link">Contact Us</Link>
                <Link to="/registered-users" className="footer-link">Registered Users</Link>
            </div>
        </footer>
    );
};

export default Footer;
