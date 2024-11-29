import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './Movies.css';

const Movies = () => {
    const [movies, setMovies] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

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
        <div className="movies-container">
            <header className="movies-title-section">
                <h1>Now Showing</h1>
                <p>Find your next favorite movie below</p>
            </header>

            {loading ? (
                <div className="movies-loading-message">Loading movies...</div>
            ) : error ? (
                <div className="movies-error-message">{error}</div>
            ) : (
                <div className="movies-list">
                    {movies.map((movie) => (
                        <div key={movie.id} className="movies-item">
                            <div className="movies-image-wrapper">
                                <img
                                    src={movie.imageUrl || '/path/to/default-movie.jpg'}
                                    alt={movie.title}
                                    className="movies-image"
                                />
                                <button
                                    onClick={() => navigate(`/movie/${movie.id}`)}
                                    className="movies-showtimes-button"
                                >
                                    SHOW TIMES
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default Movies;
