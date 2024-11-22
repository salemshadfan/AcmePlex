import React, { useState, useEffect } from 'react';
import Login from './components/login';
import SignUp from './components/signup';
import Logout from './components/signup';

const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        // Check sessionStorage for login status on page load
        const loginStatus = sessionStorage.getItem('isLoggedIn') === 'true';
        setIsLoggedIn(loginStatus);
    }, []);

    return (
        <div>
            <h1>React Auth Flow</h1>
            {isLoggedIn ? (
                <div>
                    <p>Welcome back!</p>
                    <Logout setIsLoggedIn={setIsLoggedIn} />
                </div>
            ) : (
                <div>
                    <Login setIsLoggedIn={setIsLoggedIn} />
                    <SignUp />
                </div>
            )}
        </div>
    );
};

export default App;
