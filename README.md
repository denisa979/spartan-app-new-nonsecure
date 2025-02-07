# Spartan Spring Boot Application

## Overview
This is the Spartan Spring Boot application. It includes an integrated Swagger UI for easy API documentation and testing.

## Prerequisites
Ensure you have the following installed on your system:
- Java 21 or higher
- Maven
- PostgreSQL (Ensure it is installed and configured correctly. The database username, password, and database name provided in the environment variables - mentioned below - should match the existing setup of PostgreSQL on your computer.)
- An IDE like IntelliJ IDEA or Eclipse (optional, for easy development)

## Environment Variables

This application requires certain environment variables to be set before running. These variables are essential for database connectivity and application configuration. Make sure to set them in your system or use a .env_app and .env_db files if you're using Docker or similar environments.

### Required Environment Variables

- SPARTAN_NON_SECURE_DB_URL - The JDBC URL of your database.
- SPARTAN_NON_SECURE_DB_USERNAME - The username for database authentication.
- SPARTAN_NON_SECURE_DB_PASSWORD - The password for database authentication.

- Example of setting environment variables in a Unix-based (Linux, macOS etc.) system:
    ```sh
    export SPARTAN_NON_SECURE_DB_URL=jdbc:postgresql://localhost:5432/spartan_db
    export SPARTAN_NON_SECURE_DB_USERNAME=yourusername
    export SPARTAN_NON_SECURE_DB_PASSWORD=yourpassword
    ```

- Example for Windows (Command Prompt):
    ```sh
    set SPARTAN_NON_SECURE_DB_URL=jdbc:postgresql://localhost:5432/spartan_db
    set SPARTAN_NON_SECURE_DB_USERNAME=yourusername
    set SPARTAN_NON_SECURE_DB_PASSWORD=yourpassword
    ```

Ensure these variables are set before running the application to avoid connectivity issues.

## Running the Application
1. Clone the repository:
    ```sh
    git clone https://github.com/CundullahT-CT/spartan-app-new-nonsecure.git
    cd spartan-app-new-nonsecure
    ```

2. Build and run the application using Maven:
    ```sh
    mvn spring-boot:run
    ```

### Running with the JAR file
1. Build the JAR file:
    ```sh
    mvn clean install
    ```

2. Run the JAR file:
    ```sh
    java -jar target/spartan-app-new-nonsecure-0.0.1-SNAPSHOT.jar
    ```

The application will run on http://localhost:8000

## Swagger Documentation

After the application is running, you can access the Swagger UI to explore the available APIs.

- Open your browser and go to the following URL:
  http://localhost:8000/swagger-ui.html

## Troubleshooting

- If the application does not start, ensure that port 8000 is not being used by another process. You can change the port by updating the application.properties file:
  server.port=8000
- Ensure all required environment variables are set correctly to avoid database connection errors.
