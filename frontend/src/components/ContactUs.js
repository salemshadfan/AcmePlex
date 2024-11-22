import React from 'react';
import './ContactUs.css';

const ContactUs = () => {
    return (
        <div className="contact-us-page">
            <h1 className="contact-us-title">Contact Us</h1>
            <p className="contact-us-description">
                If you have a question, comment, or require assistance regarding AcmePlex Cinemas’ products or services,
                please let us know and a member of our Guest Services Team will respond to your inquiry within 48 hours.
            </p>
            <p className="contact-us-email">Email contact: <a href="mailto:acmeplex@hotmail.com">acmeplex@hotmail.com</a></p>
            <p className="contact-us-phone">Or you can drop us a line or give us a call:</p>
            <p className="contact-us-phone">Phone number: +1 888 535 4234</p>
        </div>
    );
};

export default ContactUs;
