import React from 'react';
import './RegisteredUser.css';

const RegisteredUser = () => {
    return (
        <div className="registered-user-page">
            <h1 className="registered-user-title">Registered User Benefits</h1>
            <p className="registered-user-description">
                As a registered user of AcmePlex Cinemas, you gain access to exclusive perks that
                enhance your movie-going experience. Here are some of the benefits you can enjoy:
            </p>

            <ul className="registered-user-benefits">
                <li><strong>Exclusive Discounts:</strong> Enjoy special ticket pricing and discounts on snacks and beverages.</li>
                <li><strong>Priority Access:</strong> Reserve seats and book tickets for upcoming blockbusters before the general public.</li>
                <li><strong>Personalized Recommendations:</strong> Get tailored movie suggestions based on your preferences.</li>
                <li><strong>Faster Checkout:</strong> Save your payment and preference details for a seamless booking experience.</li>
                <li><strong>Loyalty Rewards:</strong> Earn points for every purchase and redeem them for free tickets and concessions.</li>
                <li><strong>Special Events:</strong> Invitations to exclusive screenings, premieres, and meet-and-greet sessions.</li>
                <li><strong>Enhanced Account Management:</strong> Track your movie history and customize your preferences.</li>
            </ul>

            <p className="registered-user-cta">
                Ready to unlock these amazing benefits? <a href="/signup">Join us today!</a>
            </p>
        </div>
    );
};

export default RegisteredUser;
