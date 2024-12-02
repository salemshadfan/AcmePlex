AcmePlex Project
AcmePlex is a movie theater ticket reservation system with a Spring Boot backend and a React frontend. Users can browse movies, select showtimes, and reserve tickets.

Prerequisites
Java 8 or higher
Node.js and npm
Python 3.6+
MySQL Server



Setup Instructions
Backend
Run the backend setup script:
python setup_backend.py
Alternatively, start the backend manually:
update src/resources/application.properties with sql server credentials
mvn spring-boot:run

Frontend
Run the frontend setup script:
python setup_frontend.py
Alternatively in frontend directory:
npm install
npm start


Troubleshooting
Ensure MySQL is running and credentials are correct.
Verify Node.js, npm, and Maven are installed.
Reinstall dependencies if issues arise:
npm install

DataInitializer.java initializes the database with dummy data.
Main java classes can be found in  src/main/java/com.acmeplex.acmeplex.entities.
All web components are in the directory frontend.

