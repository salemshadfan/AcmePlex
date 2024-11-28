import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App'; // Make sure the path to App.js is correct
import './index.css'; // Import global styles (optional)

const root = ReactDOM.createRoot(document.getElementById('root')); // Ensure the ID matches the one in index.html
root.render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);
