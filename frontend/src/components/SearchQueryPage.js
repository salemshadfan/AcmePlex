import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './Movies.css'; // Reuse the same styles from Movies component

const SearchQueryPage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const [searchResults, setSearchResults] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Extract the query parameter from the URL
    const queryParams = new URLSearchParams(location.search);
    const query = queryParams.get('query');

    useEffect(() => {
        const fetchMovies = async () => {
            if (!query) return;

            setLoading(true);
            try {
                const response = await fetch(`http://localhost:8080/api/movies/search?query=${encodeURIComponent(query)}`);
                if (!response.ok) {
                    throw new Error('Failed to fetch search results');
                }
                const data = await response.json();
                setSearchResults(data);
                setLoading(false);
            } catch (error) {
                setError(error.message);
                setLoading(false);
            }
        };

        fetchMovies();
    }, [query]);

    return (
        <div className="movies-container">
            <header className="movies-title-section">
                <h1>Search Results for: "{query}"</h1>
                <p>{searchResults.length} results found</p>
            </header>

            {loading ? (
                <div className="movies-loading-message">Loading search results...</div>
            ) : error ? (
                <div className="movies-error-message">{error}</div>
            ) : searchResults.length > 0 ? (
                <div className="movies-list">
                    {searchResults.map((movie) => (
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
                            <div className="movies-title">
                                <h3>{movie.title}</h3>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <div className="movies-error-message">No results found for "{query}".</div>
            )}
        </div>
    );
};

export default SearchQueryPage;
