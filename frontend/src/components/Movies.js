import React, { useState } from 'react';
import './Movies.css'; // Add styles for the Movies page

const Movies = () => {
    const [location, setLocation] = useState('NW');

    const movies = [
        { name: 'Movie1', date: '11/22/2024', image: '/path/to/movie1.jpg' },
        { name: 'Movie2', date: '11/23/2024', image: '/path/to/movie2.jpg' },
        { name: 'Movie3', date: '11/24/2024', image: '/path/to/movie3.jpg' },
        { name: 'Movie4', date: '11/25/2024', image: '/path/to/movie4.jpg' },
        { name: 'Movie5', date: '11/26/2024', image: '/path/to/movie5.jpg' },
        { name: 'Movie6', date: '11/27/2024', image: '/path/to/movie6.jpg' },
        { name: 'Movie7', date: '11/28/2024', image: '/path/to/movie7.jpg' },
        { name: 'Movie8', date: '11/29/2024', image: '/path/to/movie8.jpg' },
        { name: 'Movie9', date: '11/30/2024', image: '/path/to/movie9.jpg' },
        { name: 'Movie10', date: '12/01/2024', image: '/path/to/movie10.jpg' },
        { name: 'Movie11', date: '12/02/2024', image: '/path/to/movie11.jpg' },
        { name: 'Movie12', date: '12/03/2024', image: '/path/to/movie12.jpg' },
        { name: 'Movie13', date: '12/04/2024', image: '/path/to/movie13.jpg' },
        { name: 'Movie14', date: '12/05/2024', image: '/path/to/movie14.jpg' },
        { name: 'Movie15', date: '12/06/2024', image: '/path/to/movie15.jpg' },
    ];

    return (
        <div className="movies-page">
            <div className="location-selector" style={{ textAlign: 'center', marginBottom: '20px' }}>
                <label htmlFor="location" style={{ fontWeight: 'bold', fontSize: '1.2rem', color: 'darkgrey' }}>Choose a location: </label>
                <select
                    id="location"
                    value={location}
                    onChange={(e) => setLocation(e.target.value)}
                    style={{ fontSize: '1rem', padding: '5px', marginLeft: '10px' }}
                >
                    <option value="NW">NW</option>
                    <option value="NE">NE</option>
                    <option value="SW">SW</option>
                    <option value="SE">SE</option>
                </select>
            </div>

            {location === 'NW' ? (
                <div className="movies-list">
                    {movies.map((movie, index) => (
                        <div key={index} className="movie-card" style={{ flex: '1 0 18%', margin: '10px' }}>
                            <img src={movie.image} alt={movie.name} className="movie-image" />
                            <h3 className="movie-name">{movie.name}</h3>
                            <p className="movie-date">{movie.date}</p>
                            <div className="movie-actions">
                                <button className="movie-ticket">🎟️</button>
                                <button className="movie-play">▶️</button>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <div className="coming-soon" style={{ fontWeight: 'bold', fontSize: '2rem', textAlign: 'center', marginTop: '50px' }}>
                    Location coming soon
                </div>
            )}
        </div>
    );
};

export default Movies;
