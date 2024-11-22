import React, { useState, useEffect } from 'react';
import Header from './components/Header'; // Import Header component
import Login from './components/login';
import SignUp from './components/signup';
import Logout from './components/logout';
import Movies from './components/Movies';
import Footer from './components/Footer';
import AboutUs from './components/AboutUs';
import ContactUs from './components/ContactUs';
import Showtimes from './components/Showtimes';
import './App.css'; // Ensure styles are added

const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [currentPage, setCurrentPage] = useState('movies'); // Default to Movies Page
    const renderPage = () => {
        switch (currentPage) {
            case 'showtimes':
                return <Showtimes />;
            case 'about':
                return <AboutUs />
            case 'contact':
                return <ContactUs />;
            // Add more cases for other pages if needed
        }
    };
    useEffect(() => {
        // Check sessionStorage for login status on page load
        const loginStatus = sessionStorage.getItem('isLoggedIn') === 'true';
        setIsLoggedIn(loginStatus);
    }, []);

    const navigateTo = (page) => setCurrentPage(page);

    return (
        <div>
            <Header onNavigate={navigateTo}/>

            {currentPage === 'movies' && <Movies/>}
            {currentPage === 'showtimes' && <Showtimes/>}
            {currentPage === 'login' && (
                <div>
                    <Login setIsLoggedIn={setIsLoggedIn}/>
                    <div style={{textAlign: 'center', marginTop: '20px',  backgroundColor: '#f8f9fa',}}>
                        <p>Don't have an account?</p>
                        <button
                            onClick={() => navigateTo('signup')}
                            style={{
                                backgroundColor: 'gold',
                                color: 'black',
                                border: 'none',
                                padding: '10px 20px',
                                cursor: 'pointer',
                                borderRadius: '5px'
                            }}
                        >
                            Sign Up
                        </button>
                    </div>
                </div>
            )}
            {currentPage === 'signup' && (
                <div>
                    <SignUp/>
                    <div style={{textAlign: 'center', marginTop: '20px',  backgroundColor: '#f8f9fa',}}>
                        <p>Already have an account?</p>
                        <button
                            onClick={() => navigateTo('login')}
                            style={{
                                backgroundColor: 'gold',
                                color: 'black',
                                border: 'none',
                                padding: '10px 20px',
                                cursor: 'pointer',
                                borderRadius: '5px'
                            }}
                        >
                            Log In
                        </button>
                    </div>
                </div>
            )}
            <div>
                {/* Render the page based on currentPage */}
                {renderPage()}
                {/* Footer with navigation */}
                <Footer setCurrentPage={setCurrentPage}/>
            </div>

            {isLoggedIn && currentPage === 'main' && <Movies/>} {/* Render MainPage if logged in */}
        </div>
    );
};

export default App;
