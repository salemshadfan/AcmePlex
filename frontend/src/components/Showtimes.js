import React, { useState } from 'react';
import './Showtimes.css';

const Showtimes = () => {
    const [selectedTimes, setSelectedTimes] = useState({}); // Use an object to track selected time per movie

    const movies = Array.from({ length: 15 }, (_, index) => ({
        id: index, // Add a unique ID for each movie
        name: `Movie${index + 1}`,
        duration: `${100 + index % 3 * 20} min`,
        image: `/path/to/movie${index + 1}.jpg`,
        showtimes: [
            '12:45 pm',
            '3:15 pm',
            '6:45 pm',
            '9:30 pm'
        ],
    }));

    const handleTimeSelect = (movieId, time) => {
        setSelectedTimes((prevSelected) => ({
            ...prevSelected,
            [movieId]: time, // Track the selected time for the specific movie
        }));
    };

    const currentDate = new Date().toLocaleDateString();

    return (
        <div className="showtimes-page">
            <h2 className="current-date">Current Date: {currentDate}</h2>
            {movies.map((movie) => (
                <div key={movie.id} className="movie-showtimes">
                    <div className="movie-details">
                        <img src={movie.image} alt={movie.name} className="movie-image" />
                        <div className="movie-info">
                            <h3 className="movie-name">{movie.name}</h3>
                            <span className="movie-duration">{movie.duration}</span>
                        </div>
                    </div>
                    <div className="showtimes-list" style={{ display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
                        {movie.showtimes.map((time, idx) => (
                            <button
                                key={idx}
                                className={`showtime-button gold-button ${selectedTimes[movie.id] === time ? 'selected' : ''}`}
                                onClick={() => handleTimeSelect(movie.id, time)}
                            >
                                {time}
                            </button>
                        ))}
                    </div>
                    <div className="view-seats-container">
                        <button
                            className="view-seats-button"
                            style={{ backgroundColor: selectedTimes[movie.id] ? 'darkgoldenrod' : '#ccc', cursor: selectedTimes[movie.id] ? 'pointer' : 'not-allowed' }}
                            disabled={!selectedTimes[movie.id]}
                        >
                            View Seats
                        </button>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default Showtimes;
