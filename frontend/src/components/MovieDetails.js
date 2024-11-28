import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './MovieDetails.css';

const MoviePage = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [movie, setMovie] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(sessionStorage.getItem('isLoggedIn') === 'true');
    const [selectedShowtime, setSelectedShowtime] = useState(null);
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/movies/${id}`);
                if (!response.ok) {
                    throw new Error('Failed to fetch movie details');
                }
                const data = await response.json();
                setMovie(data);
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        fetchMovie();
    }, [id]);

    const handleShowtimeClick = (showtime) => {
        if (isLoggedIn) {
            // Proceed directly to the booking page as a registered user
            navigate(`/booking/${showtime.id}`);
        } else {
            // Show the modal for guest/login/sign-up options
            setSelectedShowtime(showtime);
            setShowModal(true);
        }
    };

    const handleGuestContinue = () => {
        setShowModal(false);
        navigate(`/booking/${selectedShowtime.id}?guest=true`);
    };

    const handleLogin = () => {
        setShowModal(false);
        navigate('/login');
    };

    const handleSignUp = () => {
        setShowModal(false);
        navigate('/signup');
    };

    if (loading) {
        return <div style={{ textAlign: 'center', fontSize: '1.5rem' }}>Loading movie details...</div>;
    }

    if (error) {
        return <div style={{ textAlign: 'center', fontSize: '1.5rem', color: 'red' }}>{error}</div>;
    }

    const groupedShowTimes = movie.showTimes.reduce((grouped, showTime) => {
        const theaterName = showTime.theatre.name;
        if (!grouped[theaterName]) {
            grouped[theaterName] = [];
        }
        grouped[theaterName].push(showTime);
        return grouped;
    }, {});

    return (
        <div className="movie-page">
            <div className="movie-page-header">
                <img
                    src={movie.imageUrl || '/path/to/default-movie.jpg'}
                    alt={movie.title}
                    className="movie-poster"
                />
                <div className="movie-details">
                    <h1 className="movie-title">{movie.title}</h1>
                    <p className="movie-metadata"><strong>Genre:</strong> {movie.genre}</p>
                    <p className="movie-metadata"><strong>Duration:</strong> {movie.duration} mins</p>
                    <p className="movie-metadata"><strong>Description:</strong> {movie.description}</p>
                </div>
            </div>
            <div className="showtimes-container">
                <h2 className="showtimes-header">Show Times</h2>
                {Object.keys(groupedShowTimes).map((theaterName) => (
                    <div key={theaterName} className="theatre-group-container">
                        <h3 className="theatre-group-title">{theaterName}</h3>
                        <p className="movie-metadata">
                            <strong>Location:</strong> {groupedShowTimes[theaterName][0].theatre.location}
                        </p>
                        <div className="showtime-buttons">
                            {groupedShowTimes[theaterName].map((showTime) => (
                                <button
                                    key={showTime.id}
                                    className="showtime-button"
                                    onClick={() => handleShowtimeClick(showTime)}
                                >
                                    {new Date(`${showTime.date}T${showTime.startTime}`).toLocaleString()}
                                </button>
                            ))}
                        </div>
                    </div>
                ))}
            </div>

            {/* Modal for login/guest/sign-up options */}
            {showModal && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h2>You need to log in to continue</h2>
                        <p>Would you like to log in, sign up, or continue as a guest?</p>
                        <div className="modal-buttons">
                            <button className="modal-button" onClick={handleLogin}>
                                Login
                            </button>
                            <button className="modal-button" onClick={handleSignUp}>
                                Sign Up
                            </button>
                            <button className="modal-button" onClick={handleGuestContinue}>
                                Continue as Guest
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default MoviePage;
