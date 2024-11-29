import os
import subprocess
import mysql.connector
from mysql.connector import Error

def update_application_properties(db_url, db_username, db_password):
    """
    Update the Spring Boot application.properties file with the provided database credentials.
    """
    properties_file = "./src/main/resources/application.properties"
    try:
        with open(properties_file, "w") as file:
            file.write(f"""
# Database Configuration
spring.datasource.url=jdbc:mysql://{db_url}/acmeplex_db
spring.datasource.username={db_username}
spring.datasource.password={db_password}

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Port
server.port=8080
            """)
        print("[INFO] Updated application.properties with provided database credentials.")
    except FileNotFoundError:
        print(f"[ERROR] Could not find {properties_file}. Ensure the path is correct.")
        exit(1)

def create_database(host, user, password):
    """
    Create the acmeplex_db database using MySQL.
    """
    try:
        connection = mysql.connector.connect(
            host=host,
            user=user,
            password=password
        )
        if connection.is_connected():
            cursor = connection.cursor()
            # Create the database
            cursor.execute("CREATE DATABASE IF NOT EXISTS acmeplex_db;")
            print("[INFO] Database 'acmeplex_db' created or already exists.")
            cursor.close()
        connection.close()
    except Error as e:
        print(f"[ERROR] Could not create the database: {e}")
        exit(1)

def start_backend():
    """
    Start the Spring Boot backend server.
    """
    print("[INFO] Starting the Spring Boot backend...")
    try:
        env = os.environ.copy()
        subprocess.run("mvn spring-boot:run", cwd="./", check=True,shell=True)
    except FileNotFoundError:
        
        print("[ERROR] Maven Wrapper (mvnw) is not found in the project directory.")
        exit(1)
    except subprocess.CalledProcessError:
        print("[ERROR] Failed to start the Spring Boot backend.")
        exit(1)

if __name__ == "__main__":
    print("Welcome to the Backend Setup Script!")
    print("Please provide the database credentials.")

    # Get database credentials from the deployer
    db_host = input("Database host (e.g., localhost): ").strip()
    db_port = input("Database port (default: 3306): ").strip() or "3306"
    db_username = input("Database Username: ").strip()
    db_password = input("Database Password: ").strip()

    db_url = f"{db_host}:{db_port}"

    # Create the database
    create_database(db_host, db_username, db_password)

    # Update the Spring Boot application.properties file
    update_application_properties(db_url, db_username, db_password)

    # Start the backend
    start_backend()
