import os
import subprocess


def start_frontend():
    """
    Start the React frontend server.
    """
    print("[INFO] Starting the React frontend...")
    try:
        subprocess.run(["npm", "install"], cwd="./frontend", check=True)
        subprocess.run(["npm", "start"], cwd="./frontend", check=True)
    except FileNotFoundError:
        print("[ERROR] Node.js is not installed or npm is not found.")
        exit(1)
    except subprocess.CalledProcessError:
        print("[ERROR] Failed to start the React frontend.")
        exit(1)

if __name__ == "__main__":
    print("Welcome to the Frontend Setup Script!")



    # Start the frontend
    start_frontend()
