import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Movies from './components/Movies';
import Login from './components/Login';
import SignUp from './components/SignUp';
import AboutUs from './components/AboutUs';
import ContactUs from './components/ContactUs';
import Showtimes from './components/Showtimes';
import Logout from './components/Logout';
import MovieDetails from './components/MovieDetails';
import Booking from './components/Booking';
import ConfirmationPage from './components/ConfirmationPage';
import './index.css';
import './App.css';

const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    // Check login status on app load and when sessionStorage changes
    useEffect(() => {
        const loginStatus = sessionStorage.getItem('isLoggedIn') === 'true';
        setIsLoggedIn(loginStatus);
    }, []);

    return (
        <Router>
            <div id="root">
                <Header isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn} />
                <div className="content">
                    <Routes>
                        {/* Public Routes */}
                        <Route path="/" element={<Movies/>}/>
                        <Route path="/about" element={<AboutUs/>}/>
                        <Route path="/contact" element={<ContactUs/>}/>
                        <Route path="/movie/:id" element={<MovieDetails/>}/>
                        <Route path="/booking/:id" element={<Booking />} />

                        <Route path="/confirmation" element={<ConfirmationPage />} />

                        {/* Auth Routes */}
                        <Route path="/login"
                               element={!isLoggedIn ? <Login setIsLoggedIn={setIsLoggedIn}/> : <Navigate to="/"/>}/>
                        <Route path="/signup" element={!isLoggedIn ? <SignUp/> : <Navigate to="/"/>}/>

                        {/* Protected Routes */}
                        <Route
                            path="/showtimes"
                            element={isLoggedIn ? <Showtimes/> : <Navigate to="/login"/>}
                        />
                        <Route
                            path="/logout"
                            element={
                                isLoggedIn ? (
                                    <Logout setIsLoggedIn={setIsLoggedIn}/>
                                ) : (
                                    <Navigate to="/login"/>
                                )
                            }
                        />

                        {/* Fallback Route */}
                        <Route path="*" element={<h1 style={{textAlign: 'center'}}>404 - Page Not Found</h1>}/>
                    </Routes>
                </div>
                    <Footer/>
                </div>
        </Router>
);
};

export default App;
