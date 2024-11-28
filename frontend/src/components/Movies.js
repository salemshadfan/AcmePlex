import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate
import './Movies.css';

const Movies = () => {
    const [location, setLocation] = useState('NW');
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate(); // Hook for navigation

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/movies/');
                if (!response.ok) {
                    throw new Error('Failed to fetch movies');
                }
                const data = await response.json();
                setMovies(data);
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        fetchMovies();
    }, []);

    return (
        <div className="movies-page">


            {loading ? (
                <div style={{ textAlign: 'center', fontSize: '1.5rem' }}>Loading movies...</div>
            ) : error ? (
                <div style={{ textAlign: 'center', fontSize: '1.5rem', color: 'red' }}>{error}</div>
            ) : location === 'NW' ? (
                <div className="movies-list">
                    {movies.map((movie) => (
                        <div
                            key={movie.id}
                            className="movie-card"
                            style={{ flex: '1 0 18%', margin: '10px' }}
                        >
                            <img
                                src={movie.imageUrl || '/path/to/default-movie.jpg'}
                                alt={movie.title}
                                className="movie-image"
                            />
                            <h3 className="movie-name">{movie.title}</h3>
                            <p className="movie-genre">{movie.genre}</p>
                            <p className="movie-duration">{movie.duration} mins</p>
                            <div className="movie-actions">
                                <button
                                    onClick={() => navigate(`/movie/${movie.id}`)} // Navigate to Movie Details page
                                    className="movie-ticket"
                                >
                                    Show Times
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <div
                    className="coming-soon"
                    style={{
                        fontWeight: 'bold',
                        fontSize: '2rem',
                        textAlign: 'center',
                        marginTop: '50px',
                    }}
                >
                    Location coming soon
                </div>
            )}
        </div>
    );
};


export default Movies;
